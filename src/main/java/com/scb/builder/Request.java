package com.scb.builder;

import com.scb.constants.Method;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Map;

@Builder
@Getter
public class Request {
    String endPoint;
    String requestBody = "";
    @Singular("queryParam")
    Map<String, String> queryParam;
    @Singular("pathParam")
    Map<String, String> pathParam;
    @Singular("headers")
    Map<String, String> headers;
    Method method;
    private String api_base_uri;
    private String api_base_path;
    private String authType;
    private String username;
    private String password;
    private boolean logging;

}
