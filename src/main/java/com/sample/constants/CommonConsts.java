package com.sample.constants;

import java.util.Arrays;
import java.util.List;

public final class CommonConsts {
    public static final String PATH_TO_CONFIGURATION_PROPERTIES = "./configuration.properties";
    public static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

    //    URL constants
    public static final String MY_IP = "https://www.myip.com/";
    public static final String GOOGLE = "https://www.google.com.ua/";

    public static String PROXY ="0";
    public static String USER_AGENT ="0";
    public static String ZERO = "0";


    //  File & Path & Sheet constants
    public static String AMAZON_CO_UK_PATH_FILE_FLIE = "..\\output\\AmazonCoUK.xlsx";
    public static String AMAZON_IN_PATH_FILE = "..\\output\\amazon.in";
    public static final String OUTPUT_DIRECTORY = "output";
    public static final String FOLDER_PATH = "..\\input";

    public static final String SHEET1 = "Sheet1";
    public static String FILE_PATH = "file.xlsx";

    public static final String firefoxWindowsAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Gecko/20100101 Firefox/47.0";
    public static final String chromeWindows = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/68.0.3440.106";
    public static final String operaWindows = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36 OPR/43.0.2442.991";
    public static final String IEWindows = "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko";
    public static final String EdgeWindows = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393";
    public static final String SafariMac = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/601.7.7 (KHTML, like Gecko) Version/9.1.2 Safari/601.7.7";


    //    Headers
    public static List<String> SAMPLE_HEADERS = Arrays.asList("URL", "Number of favourites", "Top 100");

    public CommonConsts() {
    }
}
