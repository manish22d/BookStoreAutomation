package com.scb.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.builder.Request;
import com.scb.constants.Endpoints;
import com.scb.constants.Method;
import com.scb.core.HttpOperation;
import com.scb.pojo.Book;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookServices {
    @Value("${api.uri}")
    private String api_base_uri;

    @Value("${api.basepath}")
    private String api_base_path;
    @Value("${api.auth.type}")
    private String authType;

    @Value("${api.auth.username}")
    private String username;

    @Value("${api.auth.password}")
    private String password;

    @Value("${api.logging}")
    private boolean logging;

    @Autowired
    HttpOperation httpOperation;

    public Response getBookList() {
        Request request = Request.builder()
                .api_base_uri(api_base_uri).api_base_path(api_base_path)
                .authType(authType).username(username).password(password)
                .logging(logging)
                .endPoint(Endpoints.BOOKS)
                .method(Method.GET)
                .build();
        return httpOperation.execute(request);
    }

    public Response getBookById(int id) {
        Map<String, String> pathParam = Map.of("id", String.valueOf(id));
        Request request = Request.builder()
                .api_base_uri(api_base_uri).api_base_path(api_base_path)
                .authType(authType).username(username).password(password)
                .logging(logging)
                .endPoint(Endpoints.BOOK_BY_ID)
                .method(Method.GET)
                .pathParam(pathParam)
                .build();
        return httpOperation.execute(request);
    }

    public Response createBook(Book book) {

        Request request = null;
        try {
            request = Request.builder()
                    .api_base_uri(api_base_uri).api_base_path(api_base_path)
                    .authType(authType).username(username).password(password)
                    .logging(logging)
                    .requestBody(new ObjectMapper().writeValueAsString(book))
                    .method(Method.POST)
                    .endPoint(Endpoints.BOOKS)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return httpOperation.execute(request);
    }

    public Response updateBook(int id, Book book) {
        Map<String, String> pathParam = Map.of("id", String.valueOf(id));
        Request request = null;
        try {
            request = Request.builder()
                    .api_base_uri(api_base_uri).api_base_path(api_base_path)
                    .authType(authType).username(username).password(password)
                    .logging(logging)
                    .method(Method.PUT)
                    .requestBody(new ObjectMapper().writeValueAsString(book))
                    .pathParam(pathParam)
                    .endPoint(Endpoints.BOOK_BY_ID)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return httpOperation.execute(request);
    }

    public Response deleteBook(int id) {
        Map<String, String> pathParam = Map.of("id", String.valueOf(id));
        Request request = Request.builder()
                .api_base_uri(api_base_uri).api_base_path(api_base_path)
                .authType(authType).username(username).password(password)
                .logging(logging)
                .method(Method.DELETE).pathParam(pathParam)
                .endPoint(Endpoints.BOOK_BY_ID)
                .build();
        return httpOperation.execute(request);
    }
}
