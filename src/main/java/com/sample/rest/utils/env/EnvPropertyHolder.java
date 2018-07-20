package com.sample.rest.utils.env;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rishchenko on 19.06.2017.
 */
public class EnvPropertyHolder {

    private static Map<String, Map<String, String>> ENV_PROPS = new HashMap<>();

    public static Map<String, Map<String, String>> getEnvPropertyMaps() {
        return ENV_PROPS;
    }


}
