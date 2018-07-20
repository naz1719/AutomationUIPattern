package com.sample.rest.utils.env.general;

/**
 * Created by rishchenko on 22.06.2017.
 */
public enum GeneralPropNames {

    //bluepipe
    gloawsuatdbt01_db_hostname("gloawsuatdbt01.db.hostname"),
    gloawsuatdbt01_db_username("gloawsuatdbt01.db.username"),

    gloawsuatdbt01_db_username_2("gloawsuatdbt01.db.username2"),
    gloawsuatdbt01_db_password_2("gloawsuatdbt01.db.password2"),
    gloawsuatdbt01_db_domain("gloawsuatdbt01.db.domain"),
    gloawsuatdbt01_db_password("gloawsuatdbt01.db.password"),

    //Adyen thirdparty
    adyen_api_username("adyen.api.username"),
    adyen_api_password("adyen.api.password"),
    adyen_api_publicKey("adyen.api.publicKey"),

    //product catalogDB
    lebarauat_db_hostname("lebarauat.db.hostname"),
    lebarauat_db_username("lebarauat.db.username"),
    lebarauat_db_password("lebarauat.db.password"),


    //purchases
    gazisrg01db_db_hostname("gazisrg01db.db.hostname"),
    gazisrg01db_db_username("gazisrg01db.db.username"),
    gazisrg01db_db_password("gazisrg01db.db.password"),
    omni_ws_username("omni.ws.username"),
    omni_ws_password("omni.ws.password"),
    omni_ws_ocpsubscriptionkey("omni.ws.ocpsubscriptionkey"),

    //LEBARA_CRMTEST_MSCRM DB credentials
    crm_db_username("crm.db.username"),
    crm_db_password("crm.db.password"),

    //LEBARA_CREDS
    leb_username("leb.username"),
    leb_password("leb.password"),
    leb_domain("leb.domain"),

    //Confluance
    confluence_spacename("confluence.spacename"),
    confluence_pagename("confluence.pagename"),
    confluence_attachmentname("confluence.attachmentname"),
    confluence_user("confluence.user"),
    confluence_password("confluence.password"),

    //Config properties
    es_internet_account_type("es.internet.account.type"),
    gb_internet_account_type("gb.internet.account.type"),
    dk_internet_account_type("gb.internet.account.type"),


    //Assigned ID to generate unique msisdn per PC
    msisdn_genaerator_id("msisdn.genaerator.id");

    private String prop;

    GeneralPropNames(String prop) {
        this.prop = prop;
    }

    public String getValue() {
        return prop;
    }

}