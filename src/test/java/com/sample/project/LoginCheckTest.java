package com.sample.project;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.core.testUtils.BaseTestClass;
import com.sample.project.bo.FrontBO;
import com.sample.project.dataprovider.DataProviderSource;
import com.sample.project.dto.ProxyBrowserDto;
import com.sample.project.dto.ProxyDto;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sample.constants.CommonConsts.*;
import static com.sample.constants.CommonConsts.EdgeWindows;

public class LoginCheckTest extends BaseTestClass {

    private ProxyBrowserDto localProxy;

    @Factory(dataProviderClass = DataProviderSource.class, dataProvider = "proxyList")
    public LoginCheckTest(ProxyBrowserDto localProxy) {
        this.localProxy = localProxy;
    }

    @Test(skipFailedInvocations = true, dataProviderClass = DataProviderSource.class, dataProvider = "search")
    public void testCheckLogin(ProxyDto proxyDto) throws Exception {

        PROXY = localProxy.getProxy();
        USER_AGENT = localProxy.getBrowser();

        String proxyInfo = PROXY;
        String host = proxyDto.getHost();
        String keywords = proxyDto.getKeyword();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);
        String win10 = "windows 10";
        String win7 = "windows 7";

        if (USER_AGENT.equals(firefoxWindowsAgent)) {
            LOG.info(host+","+currentTime+","+keywords+","+"firefox"+","+win10 + ","+proxyInfo);
        } else if (USER_AGENT.equals(chromeWindows)) {
            LOG.info(host+","+currentTime+","+keywords+","+"Chrome" +","+win10 + ","+proxyInfo);
        } else if (USER_AGENT.equals(operaWindows)) {
            LOG.info(host+","+currentTime+","+keywords+","+"Opera" +","+win10 + ","+proxyInfo);
        } else if (USER_AGENT.equals(IEWindows)) {
            LOG.info(host+","+currentTime+","+keywords+","+"IE" + ","+win7 + ","+proxyInfo);
        } else if (USER_AGENT.equals(EdgeWindows)) {
            LOG.info(host+","+currentTime+","+keywords+","+"EDGE" + ","+win10 + ","+proxyInfo);
        }

        FrontBO frontBO = new FrontBO();
        frontBO.openPortal(GOOGLE);
        WebElement webElement = null;
        try {
            webElement = WebDriverManager.getDriver().findElement(By.name("q"));
        } catch (Exception e) {
            WebDriverManager.refresh();
            try {
                webElement = WebDriverManager.getDriver().findElement(By.name("q"));
            } catch (Exception e2) {
                LOG.info("The Chrome with ip " + PROXY + "  not loaded");
                Assert.fail("The Chrome with ip" + PROXY + "  not loaded");
            }
        }

        waitManager.fluentElementWait(webElement);

        webElement.sendKeys(proxyDto.getKeyword());
        webElement.sendKeys(Keys.ENTER);

        long startTime = System.currentTimeMillis();

//         Randomizer
        List<Integer> list = new ArrayList<>();
        list.add(50000);
        list.add(30000);
//        list.add(10000);
//        list.add(12000);

        Random randomizer = new Random();
        Integer random = list.get(randomizer.nextInt(list.size()));

        frontBO.logic(proxyDto.getHost(), proxyDto.getKeyword(), random);

//        long endTime = System.currentTimeMillis();
//        long duration = (endTime - startTime);

//        step(String.format("All time one site - %02d min, %02d sec, Proxy IP %s",
//                TimeUnit.MILLISECONDS.toMinutes(duration),
//                TimeUnit.MILLISECONDS.toSeconds(duration) -
//                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)), PROXY));
        PROXY = "0";
    }
}
