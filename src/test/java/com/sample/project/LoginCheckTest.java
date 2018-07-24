package com.sample.project;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.core.testUtils.BaseTestClass;
import com.sample.project.bo.FrontBO;
import com.sample.project.dataprovider.DataProviderSource;
import com.sample.project.dto.ProxyDto;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.sample.constants.CommonConsts.GOOGLE;

public class LoginCheckTest extends BaseTestClass {

    @Test(skipFailedInvocations = true, dataProviderClass = DataProviderSource.class, dataProvider = "search")
    public void testCheckLogin(ProxyDto proxyDto) {
        FrontBO frontBO = new FrontBO();
        frontBO.openPortal(GOOGLE);
        WebElement webElement = WebDriverManager.getDriver().findElement(By.name("q"));
        waitManager.fluentElementWait(webElement);

        step("Search by " + proxyDto.getKeyword());
        webElement.sendKeys(proxyDto.getKeyword());
        webElement.sendKeys(Keys.ENTER);
        waitManager.waitForPageToBeReady();

        long startTime = System.currentTimeMillis();
        frontBO.logic(proxyDto.getHost(), proxyDto.getKeyword());

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        step(String.format("%02d min, %02d sec",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))));

//        List<Integer> list = new ArrayList<>();
//        Random randomizer = new Random();
//        Integer random = list.get(randomizer.nextInt(list.size()));
//        WaitManager.waitTimeOut(random);

    }
}
