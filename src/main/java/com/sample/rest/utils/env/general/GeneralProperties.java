package com.sample.rest.utils.env.general;


import com.sample.rest.utils.env.EnvPropertyHolder;
import com.sample.rest.utils.env.EnvUtil;
import com.sample.rest.utils.env.PropertiesMap;

import java.util.Map;

/**
 * Created by rishchenko on 19.06.2017.
 */
public class GeneralProperties {


    private static Map<String, String> getGeneralPropsMap() {
        if (EnvPropertyHolder.getEnvPropertyMaps().size() == 0) {
            throw new RuntimeException("ERROR: Environment property holder is not init before run");
        }
        return EnvPropertyHolder.getEnvPropertyMaps().get(PropertiesMap.general);
    }

    public static String getGeneralProperty(GeneralPropNames generalPropNames) {
        String result = getGeneralPropsMap().get(EnvUtil.ENV_TYPE + "." + generalPropNames.getValue());
        validateProp(result, generalPropNames.getValue());
        return result;
    }

    public static String getGeneralCommonProperty(GeneralPropNames generalPropNames) {
        String result = getGeneralPropsMap().get(generalPropNames.getValue());
        validateProp(result, generalPropNames.getValue());
        return result;
    }

    private static void validateProp(String result, String porpName) {
        if (result == null || result.isEmpty()) {
            throw new RuntimeException("ERROR: The property [" + porpName + "] in general.properties file is missing or empty");
        }
    }


}
