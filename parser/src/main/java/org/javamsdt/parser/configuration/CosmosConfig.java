package org.javamsdt.parser.configuration;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;

public class CosmosConfig {

    public static CosmosClient client() {
        String cosmosDbEndpoint = "https://localhost:8081"; // Emulator endpoint
        String cosmosDbKey = "C2y6yDjf5/R+ob0N8A7Cgv30VRDJIWEHLM+4QDU5DE2nQ9nDuVTqobD4b8mGGyPMbIZnqyMsEcaGQy67XIw/Jw=="; // Replace with your emulator key
        System.setProperty("javax.net.ssl.trustStore", "NUL");
        System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");

        return new CosmosClientBuilder()
                .endpoint(cosmosDbEndpoint)
                .key(cosmosDbKey)
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .contentResponseOnWriteEnabled(true)
                .buildClient();
    }

    public static CosmosDatabase cosmosDatabase() {
        String database = "Database";
        return client().getDatabase(database);
    }

    public static CosmosContainer cosmosContainer() {
        String container = "container";
        return cosmosDatabase().getContainer(container);
    }

    public static void closeCosmos() {
        client().close();
    }
}
