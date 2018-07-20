package com.sample.rest.utils;

public enum A2TimeZoneMapping {

    GB("Europe/London"),
    NL("Europe/Amsterdam"),
    ES("Europe/Madrid"),
    FR("Europe/Paris"),
    DE("Europe/Berlin"),
    DK("Europe/Copenhagen"),;

    private String prop;

    A2TimeZoneMapping(String prop) {
        this.prop = prop;
    }

    public String getZone() {
        return prop;
    }


}
