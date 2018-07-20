package com.sample.rest.services.oauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTokenByPasswordResponse {


    @JsonProperty("access_token")
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "GetTokenByPasswordResponse{" +
                "access_token='" + access_token + '\'' +
                '}';
    }
}