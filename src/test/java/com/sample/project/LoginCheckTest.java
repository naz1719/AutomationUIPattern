package com.sample.project;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.core.utils.WaitManager;
import com.sample.project.bo.FrontBO;
import com.sample.core.core.injector.Injector;
import com.sample.core.testUtils.BaseTestClass;
import com.sample.project.testUtils.DataProviderSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sample.constants.CommonConsts.WEB_SITE_URL;

public class LoginCheckTest extends BaseTestClass {

    @Injector
    private FrontBO frontBO;

    @Test(skipFailedInvocations = true, dataProviderClass = DataProviderSource.class, dataProvider = "webSite")
    public void testCheckLogin() {
        frontBO.openPortal(WEB_SITE_URL);
        WebDriverManager.getDriver().manage().window().maximize();

        WebElement webElement = WebDriverManager.getDriver().findElement(By.name("q"));
        waitManager.fluentElementWait(webElement);

        webElement.sendKeys("Hey");
        webElement.sendKeys(Keys.ENTER);


        List<Integer> list = new ArrayList<>();
        Random randomizer = new Random();
        Integer random = list.get(randomizer.nextInt(list.size()));

        WaitManager.waitTimeOut(random);

//        frontBO
//                .clickOnBuildingBlocks();
    }
}
