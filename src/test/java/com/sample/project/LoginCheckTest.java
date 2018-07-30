package com.sample.project;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.core.testUtils.BaseTestClass;
import com.sample.project.bo.FrontBO;
import com.sample.project.dataprovider.DataProviderSource;
import com.sample.project.dto.ProxyDto;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.sample.constants.CommonConsts.GOOGLE;
import static com.sample.constants.CommonConsts.PROXY;

public class LoginCheckTest extends BaseTestClass {

    private String localProxy;

    @Factory(dataProviderClass = DataProviderSource.class, dataProvider = "proxyList")
    public LoginCheckTest(String localProxy) {
        this.localProxy = localProxy;
    }

    @Test(skipFailedInvocations = true, dataProviderClass = DataProviderSource.class, dataProvider = "search")
    public void testCheckLogin(ProxyDto proxyDto) {
        PROXY = localProxy;
        FrontBO frontBO = new FrontBO();
        frontBO.openPortal(GOOGLE);
        WebElement webElement = WebDriverManager.getDriver().findElement(By.name("q"));
        waitManager.fluentElementWait(webElement);

        step("Search by " + proxyDto.getKeyword());
        webElement.sendKeys(proxyDto.getKeyword());
        webElement.sendKeys(Keys.ENTER);
        waitManager.waitForPageToBeReady();

        long startTime = System.currentTimeMillis();

//         Randomizer
        List<Integer> list = new ArrayList<>();
        list.add(4000);
        list.add(6000);
//        list.add(10000);
//        list.add(12000);

        Random randomizer = new Random();
        Integer random = list.get(randomizer.nextInt(list.size()));

        frontBO.logic(proxyDto.getHost(), proxyDto.getKeyword(), random);

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        step(String.format("%02d min, %02d sec",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))));
        PROXY = "0";
    }
}
