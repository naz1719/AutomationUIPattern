package com.sample.rest.services;

import com.jayway.restassured.response.Response;
import org.testng.Assert;

public class BaseCall {

    protected void validateResponseBasic(Response response) {
        if (response == null) {
            Assert.fail("FAIL: response is null");
        }
    }


}

