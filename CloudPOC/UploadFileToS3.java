import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CompleteMultipartUploadRequest;
import software.amazon.awssdk.services.s3.model.CompletedPart;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadRequest;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.UploadPartRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UploadFileToS3 implements S3BucketClient {
    private static final int MINIMAL_ALLOWED_MULTIPART_SIZE = 5_242_880;
    private static final int CHUNK_SIZE = 5_242_880;

    private final S3Client client;
    private final String bucketName;

    public S3BucketClientImpl(String bucketName, Region region) {
        this.bucketName = bucketName;
        this.client = S3Client.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

    }

    public void populateReport(String exportFileName, String tempFileName) {
        try {
            long fileSize = Files.size(Path.of(tempFileName));

            boolean sent;
            if (fileSize > MINIMAL_ALLOWED_MULTIPART_SIZE) {
                sent = sendInParts(exportFileName, tempFileName);
            } else {
                sent = sendAsWhole(exportFileName, tempFileName);
            }

            if (!sent) {
                throw new TechnicalException("Unexpected error while sending file to S3");
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException(e);
        }
    }

    private boolean sendAsWhole(String exportFileName, String tempFileName) {
        try {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(exportFileName)
                    .build();
            client.putObject(objectRequest,
                    RequestBody.fromFile(Path.of(tempFileName)));
        } catch (S3Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    private boolean sendInParts(String exportFileName, String tempFileName) {
        CreateMultipartUploadRequest multipartRequest = CreateMultipartUploadRequest.builder()
                .bucket(bucketName)
                .key(exportFileName)
                .build();
        CreateMultipartUploadResponse response = client.createMultipartUpload(multipartRequest);
        String uploadId = response.uploadId();
        List<CompletedPart> completedParts = new ArrayList<>();
        byte[] buffer = new byte[CHUNK_SIZE];
        try (InputStream inputStream = new FileInputStream(tempFileName)) {
            int partNumber = 0;
            int count;
            while ((count = inputStream.read(buffer)) != -1) {
                UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                        .bucket(bucketName)
                        .key(exportFileName)
                        .uploadId(uploadId)
                        .partNumber(++partNumber)
                        .build();
                String etag = client
                        .uploadPart(uploadPartRequest,
                                RequestBody.fromByteBuffer(ByteBuffer.wrap(buffer, 0, count)))
                        .eTag();
                completedParts.add(CompletedPart.builder().partNumber(partNumber).eTag(etag).build());
            }

            CompleteMultipartUploadRequest completeMultipartUploadRequest = CompleteMultipartUploadRequest.builder()
                    .bucket(bucketName)
                    .key(exportFileName)
                    .uploadId(uploadId)
                    .multipartUpload(mu -> mu.parts(completedParts))
                    .build();

            client.completeMultipartUpload(completeMultipartUploadRequest);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
