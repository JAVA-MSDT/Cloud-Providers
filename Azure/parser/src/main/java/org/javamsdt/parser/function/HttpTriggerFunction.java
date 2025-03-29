package org.javamsdt.parser.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.RequiredArgsConstructor;
import org.javamsdt.parser.configuration.CosmosConfig;
import org.javamsdt.parser.model.User;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerFunction {


    /**
     * This function listens at endpoint "/api/parser". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/parser
     * 2. curl "{your host}/api/parser?name=HTTP%20Query"
     */
    @FunctionName("parser")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = { HttpMethod.GET, HttpMethod.POST },
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<String> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Deserialize the incoming JSON
        ObjectMapper objectMapper = new ObjectMapper();
        User user;
        try {
            user = objectMapper.readValue(request.getBody(), User.class);
            System.out.println("User Parsed  Done.....");
        } catch (Exception e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Invalid JSON input").build();
        }
        System.out.println("User response:: " + user);
        try {
            CosmosConfig.cosmosContainer().createItem(user);
            CosmosConfig.closeCosmos();
            System.out.println("Data inserted.....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request.createResponseBuilder(HttpStatus.OK).body(user).build();
    }
}
