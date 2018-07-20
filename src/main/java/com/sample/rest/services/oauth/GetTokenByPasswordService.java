package com.sample.rest.services.oauth;

import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.internal.mapper.ObjectMapperType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.sample.rest.log.AllureLogger;
import com.sample.rest.services.BaseCall;
import com.sample.rest.services.oauth.dto.GetTokenByPasswordResponse;
import io.qameta.allure.Step;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.jayway.restassured.RestAssured.given;


public class GetTokenByPasswordService extends BaseCall {
    private static GetTokenByPasswordService instance;

    public static GetTokenByPasswordService getInstance() {
        if (instance == null) {
            instance = new GetTokenByPasswordService();
        }
        return instance;
    }

    @Step(value = "REST:getTokenByPassword {username} username")
    public String getTokenByPassword(String username, String password, String client_id, String client_secret) throws Exception {
        //required for logging request/response
        ByteArrayOutputStream requestStream = new ByteArrayOutputStream();
        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
        PrintStream requestVar = new PrintStream(requestStream, true);
        PrintStream responseVar = new PrintStream(responseStream, true);
        GetTokenByPasswordResponse getTokenByPasswordResponse;
        try {
//            String rawEndpoint = EnvUtil.getOmniEndpoint(EndpointPropNames.oauthAccessToken);
            String rawEndpoint = "";
            RequestSpecification request = given().filters(new ResponseLoggingFilter(LogDetail.ALL, responseVar),
                    new RequestLoggingFilter(LogDetail.ALL, requestVar)).
                    contentType("application/json").
                    queryParameter("client_id", client_id).
                    queryParameter("client_secret", client_secret).
                    queryParameter("grant_type", "password").
                    queryParameter("username", username).
                    queryParameter("password", password);
            Response response = request.when().post(rawEndpoint);
            getTokenByPasswordResponse = response.then().extract().as(GetTokenByPasswordResponse.class, ObjectMapperType.JACKSON_2);
            validateResponseBasic(response);
            AllureLogger.attachJsone("getTokenByPasswordRequest :" + username, requestStream);
            AllureLogger.attachJsone("getTokenByPasswordResponse :" + username, responseStream);
            if (getTokenByPasswordResponse.getAccess_token() == null || getTokenByPasswordResponse.getAccess_token().isEmpty()) {
                throw new RuntimeException("access token is missing, Actual Response is:" + response.prettyPrint());
            }
        } catch (Exception e) {
            throw e;
        }
        return getTokenByPasswordResponse.getAccess_token();

    }


}