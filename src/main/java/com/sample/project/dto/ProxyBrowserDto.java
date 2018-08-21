package com.sample.project.dto;

public class ProxyBrowserDto {
    String proxy;
    String browser;

    public ProxyBrowserDto(String proxy, String browser) {
        this.proxy = proxy;
        this.browser = browser;
    }

    @Override
    public String toString() {
        return "ProxyBrowserDto{" +
                "proxy='" + proxy + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
