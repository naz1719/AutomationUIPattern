package com.sample.project.dto;

public class GeneralDto {

    private ProxyBrowserDto proxyBrowserDto;
    private ProxyDto proxyDto;

    public GeneralDto(ProxyBrowserDto proxyBrowserDto, ProxyDto proxyDto) {
        this.proxyBrowserDto = proxyBrowserDto;
        this.proxyDto = proxyDto;
    }

    public ProxyBrowserDto getProxyBrowserDto() {
        return proxyBrowserDto;
    }

    public void setProxyBrowserDto(ProxyBrowserDto proxyBrowserDto) {
        this.proxyBrowserDto = proxyBrowserDto;
    }

    public ProxyDto getProxyDto() {
        return proxyDto;
    }

    public void setProxyDto(ProxyDto proxyDto) {
        this.proxyDto = proxyDto;
    }
}
