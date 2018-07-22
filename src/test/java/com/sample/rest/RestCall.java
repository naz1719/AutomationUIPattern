package com.sample.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.sample.parsers.excel.bhv.SampleBhv;
import com.sample.rest.dto.etsy.ResponseDto;
import com.sample.rest.dto.etsy.Result;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RestCall {

    public static final String endpoint = "https://openapi.etsy.com/v2/listings/active.js";
    public static final String keywords = "dog beds";
    public static final String limit = "100";
//    public static final String apikey = "3r7fxltznu2w09hlujrcxyzw";
    public static final String apikey = "cy6fzjs6b45zzmgeny0vkztv";


    public static void main(String[] args) throws Exception {

        SampleBhv sampleBhv = new SampleBhv();

        Integer offset = 0;
        ResponseDto getResponse = getResponseDto(endpoint, keywords, limit, apikey, String.valueOf(offset));
        System.out.println("Count searched items " + getResponse.getCount());

        List<Result> list = new ArrayList<>(getResponse.getResults());
        System.out.println("Item count per call " + list.size());

        while (offset < 14000) {
            System.out.println("Iteration , offset " + offset);
            offset = offset + 100;
            ResponseDto getResponse1 = getResponseDto(endpoint, keywords, limit, apikey, String.valueOf(offset));
            list.addAll(getResponse1.getResults());

        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String jsonInString = "";
        for (Result item : list) {
             jsonInString = jsonInString.concat(mapper.writeValueAsString(item));
        }

        File file = new File("all.txt");
        FileUtils.writeStringToFile(file, jsonInString);

        Collections.sort(list);
        String jsonInString1 = "";
        for (Result item : list) {
            jsonInString = jsonInString1.concat(mapper.writeValueAsString(item));
        }
        File file2 = new File("sorted.txt");
        FileUtils.writeStringToFile(file2, jsonInString);

        List<Result> top10 = new ArrayList<>(list.subList(list.size() - 100, list.size()));
        Collections.sort(top10, Collections.reverseOrder());

//        Excel writer
        sampleBhv.writeHeaders();
        sampleBhv.writeBodyOfCell(top10);

    }

    private static ResponseDto getResponseDto(String endpoint, String keywords, String limit, String apikey, String offset) throws java.io.IOException {
        Response response = new RestCall().getResponse(keywords, limit, apikey, endpoint, offset);
        String stringResponse = response.asString().replace("/**/(", "").replace(");", "");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(stringResponse, ResponseDto.class);
    }

//    public static List<OrderItem> getJsonItems(String orderItemsList) throws java.io.IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//        return mapper.readValue(orderItemsList, mapper.getTypeFactory().constructCollectionType(List.class, OrderItem.class));
//    }

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
}
