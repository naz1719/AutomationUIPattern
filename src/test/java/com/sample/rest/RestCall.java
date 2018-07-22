package com.sample.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.sample.parsers.excel.bhv.SampleBhv;
import com.sample.rest.dto.etsy.ResponseDto;
import com.sample.rest.dto.etsy.Result;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RestCall {

    public Response getResponse(String keywords, String limit, String apikey, String endpoint, String offset) {
        HashMap<String, String> hmap = new HashMap<>();
        hmap.put("keywords", keywords);
        hmap.put("limit", limit);
        hmap.put("api_key", apikey);
        hmap.put("offset", offset);

        return RestAssured.given().
                relaxedHTTPSValidation().
                params(hmap).
                when().
                get(endpoint);
    }

    public Response tmpRequest(String endpoint) {
        return RestAssured.given().
                relaxedHTTPSValidation().
                when().
                get(endpoint);
    }

    public static String getBrowserIP() throws IOException {
        String endPoint = "https://api.ipify.org?format=json";
        RestCall restCall = new RestCall();
        String json = restCall.tmpRequest(endPoint).asString();
        ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
        String ip = null;
        if (node.has("ip")) {
            System.out.println("ip: " + node.get("ip"));
            ip = String.valueOf(node.get("ip"));
        }
        if (Objects.isNull(ip)) {
            throw new RuntimeException("ip is null");
        }
        return ip;
    }

    public static void main(String[] args) throws IOException {
        getBrowserIP();
    }
}
