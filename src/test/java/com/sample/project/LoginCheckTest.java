package com.sample.project;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.core.testUtils.BaseTestClass;
import com.sample.project.bo.FrontBO;
import com.sample.project.dataprovider.DataProviderSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.sample.constants.CommonConsts.GOOGLE;

public class LoginCheckTest extends BaseTestClass {

    @Test(skipFailedInvocations = true, dataProviderClass = DataProviderSource.class, dataProvider = "search")
    public void testCheckLogin(String search) {
        FrontBO frontBO = new FrontBO();
        frontBO.openPortal(GOOGLE);
        WebElement webElement = WebDriverManager.getDriver().findElement(By.name("q"));
        waitManager.fluentElementWait(webElement);

        step("Search by " + search);
        webElement.sendKeys(search);
        webElement.sendKeys(Keys.ENTER);
        frontBO.logic();

//        List<Integer> list = new ArrayList<>();
//        Random randomizer = new Random();
//        Integer random = list.get(randomizer.nextInt(list.size()));
//        WaitManager.waitTimeOut(random);

    }
}
