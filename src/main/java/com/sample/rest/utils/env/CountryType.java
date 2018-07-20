package com.sample.rest.utils.env;


public enum CountryType {

    GB("gb"),
    ES("es"),
    DE("de"),
    FR("fr"),
    DK("dk"),
    NL("nl");

    private String countryType;

    CountryType(String countryType) {
        this.countryType = countryType;
    }

    public String getCountryType() {
        return this.countryType;
    }


}
