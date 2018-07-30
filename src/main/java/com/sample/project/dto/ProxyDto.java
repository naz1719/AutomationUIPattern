package com.sample.project.dto;

public class ProxyDto {
    private String keyword;
    private String host;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ProxyDto(String ketword, String host) {
        this.keyword = ketword;
        this.host = host;
    }
}
