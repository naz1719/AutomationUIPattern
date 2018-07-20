package com.sample.rest.utils.env;

/**
 * Created by rishchenko on 22.06.2017.
 */
public enum UriPropNames {

    example("example");

    private String prop;

    UriPropNames(String prop) {
        this.prop = prop;
    }

    public String getValue() {
        return prop;
    }

}