# Azure Function - HTTP Trigger Example

## Table of Contents

- [Description](#description)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Local Configuration](#local-configuration)

## Description

This Azure Function is designed to be triggered via an HTTP call. It utilizes the Jackson library to parse incoming JSON data into a User object and then stores the parsed User object in Cosmos DB. This README.md file provides detailed information on the purpose of the function and how to set it up for local development.

## Getting Started

To set up and run this Azure Function locally, follow the steps below:

### Prerequisites

Make sure you have the following frameworks and libraries installed on your local development environment:

- [Jackson library](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
- [Azure Functions Core Tools](https://docs.microsoft.com/en-us/azure/azure-functions/functions-run-local) 
- [Azure Cosmos DB Emulator](https://docs.microsoft.com/en-us/azure/cosmos-db/local-emulator) - [Download locally](https://learn.microsoft.com/en-us/azure/cosmos-db/emulator-release-notes)

### Local Configuration

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/JAVA-MSDT/azure-function-POC

2. Make sure that you have [Azure Functions Core Tools](https://docs.microsoft.com/en-us/azure/azure-functions/functions-run-local) & [Azure Cosmos DB Emulator](https://learn.microsoft.com/en-us/azure/cosmos-db/emulator-release-notes) downloaded & installed locally.
3. Using your IDE (Maven extension) `mvn clean package` then `mvn azure-functions:run` or just Run the function itself from the green Run beside the function name.
4. Using Postman you can send a request, attached a [Postan collection](/AzureFunction.postman_collection.json) for the easy testing.
5. Check the console the running port, because sometimes the port of randomly generated every run.
6. Make sure that you have run the Cosmos Emulator.





