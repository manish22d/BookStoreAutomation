package com.scb.stepdef.functional;

import com.scb.utils.ScenarioContext;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusCodeSteps {
    @Autowired
    ScenarioContext context;

    @Then("transaction should complete successfully")
    public void transaction_completed_successfully() {
        Response response = (Response) context.getContext("response");
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }



    @Then("transaction completed with bad request")
    public void transaction_completed_with_bad_request() {
        Response response = (Response) context.getContext("response");
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Then("transaction should complete with authentication error")
    public void transaction_completed_with_authentication_error() {
        Response response = (Response) context.getContext("response");
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Then("transaction completed with not found request")
    public void transaction_completed_with_not_found_status() {
        Response response = (Response) context.getContext("response");
        response.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Then("transaction completed with unsupported media type error")
    public void transaction_completed_with_unsupported_media_type() {
        Response response = (Response) context.getContext("response");
        response.then().assertThat().statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }
}
