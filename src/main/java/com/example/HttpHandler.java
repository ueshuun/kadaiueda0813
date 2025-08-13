package com.example;

import com.example.model.SampleModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

public class HttpHandler extends FunctionInvoker<SampleModel, String> {

        @FunctionName("SELECT")
        public HttpResponseMessage runSelect(
        @HttpTrigger(
                name = "selectReq",methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context) {

                context.getLogger().info("Java HTTP trigger function 'SELECT' invoked.");
                SampleModel sample = new SampleModel(0,"", 0,0);
                String responseMessage = handleRequest(sample, context); 
                return request.createResponseBuilder(HttpStatus.OK).body(responseMessage).build();
        
        }

        @FunctionName("INSERT")
        public HttpResponseMessage runInsert(
        @HttpTrigger(
                name = "insertReq",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context) {

                context.getLogger().info("Java HTTP trigger function 'INSERT' invoked.");

                String idStr = request.getQueryParameters().get("id");
                String productStr = request.getQueryParameters().get("product");
                String priceStr = request.getQueryParameters().get("price");
                String quantityStr = request.getQueryParameters().get("quantity");

                Optional<String> requestBodyOptional = request.getBody();
                if (requestBodyOptional.isPresent()) {
                        try {
                                ObjectMapper mapper = new ObjectMapper();
                                JsonNode jsonNode = mapper.readTree(requestBodyOptional.get());
                                if (jsonNode.has("id")) idStr = jsonNode.get("id").asText();
                                if (jsonNode.has("product")) productStr = jsonNode.get("product").asText();
                                if (jsonNode.has("price")) priceStr = jsonNode.get("price").asText();
                                if (jsonNode.has("quantity")) quantityStr = jsonNode.get("quantity").asText();
                        } catch (JsonProcessingException e) {
                                context.getLogger().severe("JSON parsing error: " + e.getMessage());
                        }
                }

                String nameStr;
                SampleModel sample = new SampleModel(Integer.parseInt(idStr), productStr, Integer.parseInt(priceStr), Integer.parseInt(quantityStr));
                String responseMessage = handleRequest(sample, context); 

                return request.createResponseBuilder(HttpStatus.OK).body(responseMessage).build();
        }

}
