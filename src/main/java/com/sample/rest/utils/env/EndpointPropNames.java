package com.sample.rest.utils.env;

/**
 * Created by rishchenko on 22.06.2017.
 */
public enum EndpointPropNames {
    paymentHistory("paymentHistory"),
    paymentHistoryGet("paymentHistoryGet"),
    paymentProcessor("paymentProcessor"),
    refund("refund"),
    adyenNotification("adyenNotification"),
    getBalance("getBalance"),
    createCustomer("createCustomer"),
    oauthAccessToken("oauthAccessToken"),
    submitOrder("submitOrder"),
    autoRenew("autoRenew"),
    sendKeywordNL("sendKeywordNL"),
    sendKeywordUK("sendKeywordUK"),
    sendKeywordDK("sendKeywordDK"),
    sendKeywordFR("sendKeywordFR"),
    sendKeywordDE("sendKeywordDE"),
    eligibilityVerification("eligibilityVerification"),
    eligibileProducts("eligibileProducts"),
    in2basic("in2basic"),
    cpaUpdate("cpaUpdate"),
    cpaGet("cpaGet"),
    delink("dlink"),
    productCatalogue("productCatalogue"),
    lebaraCRM("lebaraCRM"),
    autoTopupConfigurationCapAdd("autoTopupConfigurationCapAdd"),
    autoTopupCapReset("autoTopupCapReset"),
    autoTopupConfiguration("autoTopupConfiguration"),
    getReccuringActive("getReccuringActive"),
    getRecurringAll("getRecurringAll"),
    autoTopUpTrigger("autoTopUpTrigger"),
    getRecurringCurrent("getRecurringCurrent");


    private String prop;

    EndpointPropNames(String prop) {
        this.prop = prop;
    }

    public String getValue() {
        return prop;
    }

}