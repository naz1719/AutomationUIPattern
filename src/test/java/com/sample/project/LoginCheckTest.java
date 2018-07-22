package com.sample.project;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.core.testUtils.BaseTestClass;
import com.sample.core.utils.WaitManager;
import com.sample.project.bo.FrontBO;
import com.sample.project.dataprovider.DataProviderSource;
import com.sample.rest.utils.AssertUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sample.constants.CommonConsts.GOOGLE;
import static com.sample.constants.CommonConsts.PROXY;
import static com.sample.constants.CommonConsts.MY_IP;
import static com.sample.parsers.fileUtils.FileUtilsWrapper.getIp;

public class LoginCheckTest extends BaseTestClass {

    @Test(skipFailedInvocations = true, dataProviderClass = DataProviderSource.class, dataProvider = "webSite")
    public void testCheckLogin(String proxy) throws IOException {
        PROXY = proxy;
        FrontBO frontBO =  new FrontBO();
//        frontBO.openPortal(MY_IP);
//        AssertUtil.assertEquals("ip", frontBO.getMyIp(), getIp(proxy));
        frontBO.openPortal(GOOGLE);

        WebElement webElement = WebDriverManager.getDriver().findElement(By.name("q"));
        waitManager.fluentElementWait(webElement);
        webElement.sendKeys("Hey");
        webElement.sendKeys(Keys.ENTER);


//        frontBO.logic();
//        List<Integer> list = new ArrayList<>();
//        Random randomizer = new Random();
//        Integer random = list.get(randomizer.nextInt(list.size()));
//        WaitManager.waitTimeOut(random);

    }
}
