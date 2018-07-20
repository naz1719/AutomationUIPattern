package com.sample.rest.utils.env;


import org.apache.commons.lang3.text.StrSubstitutor;
import utils.CustomUtils;
import utils.PropertyUtils;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnvUtil {

    public static final String ENV_TYPE = checkEnvType(System.getProperty("envType")).toLowerCase();
    private static final String THIRD_PARTY_HOSTS_PROPERTIES_PATH = "hosts.properties";
    private static final String THIRD_PARTY_URIS_PROPERTIES_PATH = "uri.properties";

    public static String getCountryPhoneCode(String country) {
        if (country.equalsIgnoreCase("fr")) {
            return "33";
        } else if (country.equalsIgnoreCase("gb")) {
            return "44";
        } else if (country.equalsIgnoreCase("de")) {
            return "49";
        } else if (country.equalsIgnoreCase("es")) {
            return "34";
        } else if (country.equalsIgnoreCase("nl")) {
            return "31";
        } else if (country.equalsIgnoreCase("dk")) {
            return "45";
        } else {
            throw new RuntimeException("EnvUtil Cannot getCountryPhoneCode()");
        }
    }

    public static String getNumberWithCountryPrefix(String msisdnWithCountryPrefix, String country) {
        if (msisdnWithCountryPrefix.startsWith("0")) {
            msisdnWithCountryPrefix = msisdnWithCountryPrefix.substring(1);
        }
        return getCountryPhoneCode(country) + msisdnWithCountryPrefix;
    }

    public static String getOmniEndpoint(EndpointPropNames endpointPropName) {
        String THIRD_PARTY_ENDPOINT_PROPERTIES_PATH = "omni/" + EnvUtil.ENV_TYPE.toLowerCase() + ".endpoint.properties";
        return PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_ENDPOINT_PROPERTIES_PATH, endpointPropName.getValue());

    }

    public static String getIN2BasicEndpointByUri(UriPropNames uriPropName) {
        return getEndpointUrl(EnvUtil.ENV_TYPE.toLowerCase() + ".in2basic.host", uriPropName.getValue());
    }

    public static String getGloawsuatdbt01ConnectionString() {
        return PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_HOSTS_PROPERTIES_PATH, EnvUtil.ENV_TYPE.toLowerCase() + "." + "gloawsuatdbt01.connect.string");
    }

    public static String getLebaraUATConnectionString() {
        return PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_HOSTS_PROPERTIES_PATH, EnvUtil.ENV_TYPE.toLowerCase() + "." + "lebarauat.connect.string");
    }

    public static String getGazisrg01dbConnectionString() {
        return PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_HOSTS_PROPERTIES_PATH, EnvUtil.ENV_TYPE.toLowerCase() + "." + "gazisrg01db.connect.string");
    }


    private static String getEndpointUrl(String hostPrp, String uriPrp) {
        String uri = PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_URIS_PROPERTIES_PATH, uriPrp);
        String host = PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_HOSTS_PROPERTIES_PATH, hostPrp);
        return host + uri;
    }

    private static String checkEnvType(String envValue) {

        for (EnvironmentType environmentType : EnvironmentType.values()) {
            if (environmentType.getEnvType().equalsIgnoreCase(envValue)) {
                break;
            }
        }
        if (envValue == null) {
            String error = "Can't start automation without Environment type. EnvType is [" + envValue + "]. Supported only " + getEnvOptions();
            throw new RuntimeException(error);
        }
        return envValue;

    }

    private static String getEnvOptions() {

        StringBuffer stringBuffer = new StringBuffer();

        for (EnvironmentType environmentType : EnvironmentType.values()) {
            stringBuffer.append("[");
            stringBuffer.append(environmentType.getEnvType());
            stringBuffer.append("]");
        }

        return stringBuffer.toString();
    }


    private static String checkCountryType(String countryValue) {

        for (EnvironmentType environmentType : EnvironmentType.values()) {
            if (environmentType.getEnvType().equalsIgnoreCase(countryValue)) {
                break;
            }
        }
        if (countryValue == null) {
            String error = "Can't start automation without Country type. CountryType is [" + countryValue + "]. Supported only " + getCountryOptions();
            throw new RuntimeException(error);
        }
        return countryValue;

    }

    private static String getCountryOptions() {

        StringBuffer stringBuffer = new StringBuffer();

        for (EnvironmentType environmentType : EnvironmentType.values()) {
            stringBuffer.append("[");
            stringBuffer.append(environmentType.getEnvType());
            stringBuffer.append("]");
        }

        return stringBuffer.toString();
    }


    public static String getCRMConnectionString() {
        HashMap<String, String> values = new HashMap();
        values.put("dbHostName", PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_HOSTS_PROPERTIES_PATH, ENV_TYPE.toLowerCase() + "." + "crm.host"));
        values.put("dbname", PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_HOSTS_PROPERTIES_PATH, ENV_TYPE.toLowerCase() + "." + "crm.db"));
        values.put("domain", "GBR.lebara");
        String conn = PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_URIS_PROPERTIES_PATH, ENV_TYPE.toLowerCase() + "." + "crm.connect.string");
        StrSubstitutor sub = new StrSubstitutor(values);
        conn = sub.replace(conn);
        return conn;
    }

    public static String getLebaraBluepipeDBName() {
        return PropertyUtils.getClasspathPropertyValue(THIRD_PARTY_URIS_PROPERTIES_PATH, "Lebara.Bluepipe_DB_Name");
    }


    public static void initEnvGeneralProperties() throws Exception {
        Map envProps = EnvPropertyHolder.getEnvPropertyMaps();
        Properties gprop = new Properties();
        gprop.load(new FileReader(new File(CustomUtils.getGeneralPropFilePath())));
        Properties configs = new Properties();
        configs.load(new FileReader(new File(CustomUtils.getConfigPropFilePath())));
        HashMap gmap = new HashMap(gprop);
        gmap.putAll(configs);
        envProps.put(PropertiesMap.general, gmap);
    }

    public static void initEnvGeneralPropertiesOmni() throws Exception {
        Map envProps = EnvPropertyHolder.getEnvPropertyMaps();
        Properties gprop = new Properties();
        gprop.load(new FileReader(new File(CustomUtils.getGeneralPropFilePath())));
        // Properties configs = new Properties();
        // configs.load(new FileReader(new File(CustomUtils.getConfigPropFilePath())));
        HashMap gmap = new HashMap(gprop);
        // gmap.putAll(configs);
        envProps.put(PropertiesMap.general, gmap);
    }


}
