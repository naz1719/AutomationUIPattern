package com.sample.core.core.driver;

import com.sample.constants.CommonConsts;
import com.sample.core.utils.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import java.io.*;

public class TorBrowser {
//    protected static PropertiesLoader propertiesLoader = new PropertiesLoader(CommonConsts.PATH_TO_CONFIGURATION_PROPERTIES);

    public static void main(String[] args) throws Exception {
// TODO Auto-generated method stub
        String path = "C:\\Users\\Nazar Khimin\\Downloads\\Tor Browser\\Browser";
//        System.setProperty("webdriver.gecko.driver", "C:/Users/Jay/Desktop/Tor Browser/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Nazar Khimin\\IdeaProjects\\AutomationUI\\web-drivers\\geckodriver.exe");

        File torProfileDir = new File("C:\\Users\\Nazar Khimin\\Downloads\\Tor Browser\\Browser\\TorBrowser\\Data\\Browser\\profile.default");
        FirefoxBinary binary = new FirefoxBinary(new File(path + "\\firefox.exe"));

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

        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary(binary);
        options.setProfile(profile);

        FirefoxDriver driver = new FirefoxDriver(options);
        Thread.sleep(10000);
//        driver.get("http://www.google.com/");
        driver.get("https://duckduckgo.com/?q=my+ip&t=h_&ia=answer");
        Thread.sleep(2000);
//        driver.get("https://www.iplocation.net/find-ip-address");
//            WebElement webElement = driver.findElement(By.name("q"));
//            webElement.sendKeys("my ip");
//            webElement.sendKeys(Keys.ENTER);

        for (int i = 0; i < 3; i++) {

            driver.navigate().refresh();
            Thread.sleep(100000);
            WebElement ip = driver.findElement(By.className("zci__body"));
            String name = ip.getText();
            System.out.println(name);
        }
//        Actions actions = new Actions(driver).sendKeys().sendKeys(Keys.ENTER);
//        actions.build();

        driver.quit();
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
