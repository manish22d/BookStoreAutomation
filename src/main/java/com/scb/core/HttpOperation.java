package com.scb.core;


import com.scb.builder.Request;
import com.scb.utils.ScenarioContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpOperation {
    @Autowired
    ScenarioContext context;

    public Response execute(Request request) {
        Response response;

        RequestSpecification requestSpecification = RestAssured.given();
        switch (request.getAuthType()) {
            case "basic" -> requestSpecification.auth().basic(request.getUsername(), request.getPassword());
            case "ntlm" -> requestSpecification.auth().ntlm(request.getUsername(),request.getPassword(), "", "");
            default -> {
            }
        }

        requestSpecification.baseUri(request.getApi_base_uri());
        requestSpecification.basePath(request.getApi_base_path());

        if (!request.getQueryParam().isEmpty()) requestSpecification.queryParams(request.getQueryParam());
        if (!request.getPathParam().isEmpty()) requestSpecification.pathParams(request.getPathParam());
        if (request.isLogging()) requestSpecification.log().all();
        if (StringUtils.isNoneBlank(request.getRequestBody())) requestSpecification.body(request.getRequestBody());
        requestSpecification.contentType(ContentType.JSON);
        if (!request.getHeaders().isEmpty()) requestSpecification.headers(request.getHeaders());

        response = switch (request.getMethod()) {
            case GET -> requestSpecification.get(request.getEndPoint());
            case POST -> requestSpecification.contentType(ContentType.JSON).post(request.getEndPoint());
            case DELETE -> requestSpecification.delete(request.getEndPoint());
            case PUT -> requestSpecification.put(request.getEndPoint());
        };
        if (request.isLogging()) response.then().log().all();
        context.setContext("response", response);
        return response;
    }


}
