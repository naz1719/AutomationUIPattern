package com.sample.rest.utils.env;

import log.AllureLogger;
import utils.CustomUtils;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnvInitializer {
    private static EnvInitializer instance;

    private EnvInitializer() {
    }

    public static EnvInitializer initEnvProperties() throws Exception {
        if (instance == null) {
            instance = new EnvInitializer();
            AllureLogger.logToslf4j = true;
            AllureLogger.logSoapCallToAllure = true;
            initEnvGeneralPropertiesOmni();
            //init MainProductID
//            ProductIdToMainProductIdHolder.getInstance();
        }
        return instance;
    }


    private static void initEnvGeneralPropertiesOmni() throws Exception {
        Map envProps = EnvPropertyHolder.getEnvPropertyMaps();
        Properties gprop = new Properties();
        gprop.load(new FileReader(new File(CustomUtils.getGeneralPropFilePath())));
        HashMap gmap = new HashMap(gprop);
        envProps.put(PropertiesMap.general, gmap);
    }




}



