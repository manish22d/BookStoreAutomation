package com.scb.stepdef.requestvalidation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.builder.Request;
import com.scb.constants.Endpoints;
import com.scb.constants.Method;
import com.scb.core.HttpOperation;
import com.scb.pojo.Book;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestValidationTest {
    @Autowired
    HttpOperation httpOperation;
    Request request;
    String book_id;
    @Value("${api.uri}")
    private String api_base_uri;
    @Value("${api.basepath}")
    private String api_base_path;
    @Value("${api.auth.type}")
    private String authType;
    @Value("${api.logging}")
    private boolean logging;
    @Value("${api.auth.username}")
    private String username;
    @Value("${api.auth.password}")
    private String password;

    @Given("user want to update book {string}")
    public void userWantToUpdateBook(String book_id) {
        this.book_id = book_id;
    }

    @Given("user want to send request without any request body")
    public void userWantToSendRequestWithoutAnyRequestBody() {

        request = Request.builder()
                .api_base_uri(api_base_uri).api_base_path(api_base_path)
                .authType(authType).username(username).password(password)
                .logging(logging)
                .requestBody("")
                .method(Method.POST)
                .endPoint(Endpoints.BOOKS)
                .build();
    }

    @Given("user want to send request with empty object")
    public void userWantToSendRequestWithEmptyObject() {

        request = Request.builder()
                .api_base_uri(api_base_uri).api_base_path(api_base_path)
                .authType(authType).username(username).password(password)
                .logging(logging)
                .requestBody("")
                .method(Method.POST)
                .endPoint(Endpoints.BOOKS)
                .build();
    }


    @And("user want to send update request with empty object")
    public void userWantToSendUpdateRequestWithEmptyObject() {
        Map<String, String> pathParam = Map.of("id", book_id);

        request = Request.builder()
                .api_base_uri(api_base_uri).api_base_path(api_base_path)
                .authType(authType).username(username).password(password)
                .logging(true)
                .method(Method.PUT)
                .requestBody("{}")
                .pathParam(pathParam)
                .endPoint(Endpoints.BOOK_BY_ID)
                .build();

    }

    @When("user triggers book request")
    public void userTriggersCreateBookRequest() {
        httpOperation.execute(request);
    }

}
