package com.sample.core.core.driver;

import com.sample.constants.CommonConsts;
import com.sample.core.testUtils.TestLogger;
import com.sample.core.utils.PropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.sample.constants.CommonConsts.PROXY;
import static com.sample.constants.CommonConsts.ZERO;


public class WebDriverFactory {
    private static final TestLogger LOG = TestLogger.getLogger();
    protected PropertiesLoader propertiesLoader = new PropertiesLoader(CommonConsts.PATH_TO_CONFIGURATION_PROPERTIES);


    public WebDriver getDriverInstance() {
        Drivers driverType = Drivers.getDriverType(propertiesLoader.getBrowserName());
        String hubURLSystemProperty = propertiesLoader.getHubURLSystemProperty();

        if (hubURLSystemProperty != null && !hubURLSystemProperty.isEmpty()) {
            driverType = Drivers.REMOTE_WEB_DRIVER;
        }

        return getDriverInstance(driverType);
    }

    public WebDriver getDriverInstance(Drivers driverType) {

        switch (driverType) {

            case CHROME:
                System.setProperty("webdriver.chrome.driver",
                        propertiesLoader.getChromeDriverPath());
                WebDriver chrome = new ChromeDriver(getChromeCapabilities());
                chrome.manage().timeouts().implicitlyWait(propertiesLoader.getImplicitlyWaitTimeout(), TimeUnit.SECONDS);
                return chrome;

            case IE:
                System.setProperty("webdriver.ie.driver",
                        propertiesLoader.getInternetExplorerDriver_32Path());
                WebDriver ieDriver = new InternetExplorerDriver(getInternetExplorerCapabilities());
                ieDriver.manage().timeouts().implicitlyWait(propertiesLoader.getImplicitlyWaitTimeout(), TimeUnit.SECONDS);
                ieDriver.manage().window().maximize();
                return ieDriver;

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", propertiesLoader.getFirefoxDriverPath());
                WebDriver firefoxDriver = new FirefoxDriver(getFirefoxCapabilities());
                firefoxDriver.manage().timeouts().implicitlyWait(propertiesLoader.getImplicitlyWaitTimeout(), TimeUnit.SECONDS);
                return firefoxDriver;
            case HTML_UNIT_DRIVER:
                HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
                htmlUnitDriver.setJavascriptEnabled(true);
                return htmlUnitDriver;

            case GHOST_DRIVER:
                DesiredCapabilities DesireCaps = new DesiredCapabilities();
//                DesireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, propertiesLoader.getGhostDriverPath());
                System.setProperty("phantomjs.binary.path", propertiesLoader.getGhostDriverPath());

                WebDriver ghostDriver = new PhantomJSDriver();
                return ghostDriver;

            case REMOTE_WEB_DRIVER:
                WebDriver driver = null;
                URL url = null;
                try {
                    String hubURL = propertiesLoader.getHubURLSystemProperty();
                    url = new URL(hubURL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver = new RemoteWebDriver(url, getChromeCapabilities());
                driver.manage().timeouts().implicitlyWait(propertiesLoader.getImplicitlyWaitTimeout(), TimeUnit.SECONDS);
                driver.manage().window().maximize();
                return driver;

        }
        throw new RuntimeException("Unsupported driver type");
    }

    private DesiredCapabilities getChromeCapabilities() {
        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

//        chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        chromeCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
//                UnexpectedAlertBehaviour.ACCEPT);
//        chromeCapabilities.setCapability("browserConnectionEnabled", true);
//
        ChromeOptions chromeOptions = new ChromeOptions();
        // Add the WebDriver proxy capability.
        chromeOptions.addArguments("--no-sandbox");
        String firefoxWindowsAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0";
        String chromeWindows = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
        String operaWindows = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.52 Safari/537.36 OPR/15.0.1147.100";
        String IEWindows = "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; AS; rv:11.0) like Gecko";
        String EdgeWindows = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393";

        List<String> list = new ArrayList<>();
        list.add(firefoxWindowsAgent);
        list.add(chromeWindows);
        list.add(operaWindows);
        list.add(IEWindows);
        list.add(EdgeWindows);


        String userAgent = list.get(anyItem(list.size()));
        chromeOptions.addArguments("--user-agent=" + userAgent);
        if (!PROXY.equals(ZERO)) {
            chromeOptions.addArguments("--proxy-server=" + PROXY);
        }
        chromeOptions.addArguments("--start-maximized");
        chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return chromeCapabilities;
    }

    public int anyItem(Integer size) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(size);
    }

    private DesiredCapabilities getFirefoxCapabilities() {
        DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        return firefoxCapabilities;
    }

    private DesiredCapabilities getInternetExplorerCapabilities() {
        DesiredCapabilities internetExplorerCapabilities = DesiredCapabilities.internetExplorer();
//        internetExplorerCapabilities.setCapability("webdriver.ie.version", "11");
//        internetExplorerCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        internetExplorerCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//        internetExplorerCapabilities.setCapability("ignoreProtectedModeSettings", true);
        return internetExplorerCapabilities;
    }
}