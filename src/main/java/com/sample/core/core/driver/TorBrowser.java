package com.sample.core.core.driver;

import com.sample.constants.CommonConsts;
import com.sample.core.utils.PropertiesLoader;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.*;

public class TorBrowser {
//    protected static PropertiesLoader propertiesLoader = new PropertiesLoader(CommonConsts.PATH_TO_CONFIGURATION_PROPERTIES);

    public static void main(String[] args) throws Exception {
// TODO Auto-generated method stub
        String path = "C:\\Users\\Nazar Khimin\\Downloads\\Tor Browser\\Browser";
//        System.setProperty("webdriver.gecko.driver", "C:/Users/Jay/Desktop/Tor Browser/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Nazar Khimin\\IdeaProjects\\AutomationUI\\web-drivers\\geckodriver.exe");
        File torProfileDir = new File("C:\\Users\\Nazar Khimin\\Downloads\\Tor Browser\\Browser\\TorBrowser\\Data\\Browser\\profile.default");
        FirefoxBinary binary = new FirefoxBinary(new File(path+"\\firefox.exe"));

        FirefoxProfile torProfile = new FirefoxProfile(torProfileDir);
        torProfile.setPreference("webdriver.load.strategy", "unstable");

        try {
            binary.startProfile(torProfile, torProfileDir, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.socks", "127.0.0.1");
        profile.setPreference("network.proxy.socks_port", 9150);
        FirefoxDriver driver = new FirefoxDriver(profile);
        Thread.sleep(10000);
        driver.get("http://www.google.com/");
        Thread.sleep(5000);
        killFirefox();
    }

    private static void killFirefox() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("taskkill /F /IM firefox.exe");
            while (processIsRunning("firefox.exe")) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean processIsRunning(String process) {
        boolean processIsRunning = false;
        String line;
        try {
            Process proc = Runtime.getRuntime().exec("wmic.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
            oStream.write("process where name='" + process + "'");
            oStream.flush();
            oStream.close();
            while ((line = input.readLine()) != null) {
                if (line.toLowerCase().contains("caption")) {
                    processIsRunning = true;
                    break;
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processIsRunning;
    }
}
