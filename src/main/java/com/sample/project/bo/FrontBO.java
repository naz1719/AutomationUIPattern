package com.sample.project.bo;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.core.utils.WaitManager;
import com.sample.project.po.FrontPO;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FrontBO extends BaseBO {
    private FrontPO frontPO;

    public FrontBO() {
        frontPO = new FrontPO();
    }

    public String getMyIp() {
        String browerIp = frontPO.getMyIp();
        step("browerIp " + browerIp);
        return browerIp;
    }

    public List<WebElement> getCaptcha() {
        return frontPO.getCaptcha();
    }


    public void logic(String host, String searchWord, int millisecond) {
        waitManager.waitForPageToBeReady();

        List<WebElement> frames = WebDriverManager.getDriver().findElements(By.xpath("//iframe"));
        if (frames.size() > 0) {
            WebDriverManager.getDriver().switchTo().frame(0);
            List<WebElement> captcha = frontPO.getCaptcha();
            if (captcha.size() > 0) {
                step("Captcha on page");
                Assert.fail("Captcha on page");
            }
        }

        List<WebElement> elementList = frontPO.getResults(host);
        if (elementList.size() > 0) {
            WebElement element = frontPO.getResults(host).get(0);
            waitManager.fluentElementWait(element);
            WebDriverManager.clickByJS(element);


            WaitManager.sleepTimeOut(millisecond);
            WebDriverManager.scrollDown();
            WaitManager.sleepTimeOut(2000);
            WebDriverManager.scrollUp();
            String url = WebDriverManager.getDriver().getCurrentUrl();
            step("Search by :" + searchWord + " and we are on " + url);
//        List<WebElement> webElementList = WebDriverManager.getDriver().findElements(By.xpath(".//a[contains(@href,'" + url + "')]"));
            List<WebElement> webElementList = WebDriverManager.getDriver().findElements(By.xpath(".//a"));
            if (webElementList.size() > 0) {
                WebElement e = webElementList.get(0);
                waitManager.fluentElementWait(e);
                WebDriverManager.clickByJS(e);
            }
            WebDriverManager.scrollDown();
            WebDriverManager.getDriver().navigate().back();
            WaitManager.sleepTimeOut(5000);
            WebDriverManager.refresh();
        } else {
            error("Not found site " + host + " with search word " + searchWord);
            Assert.fail("Not found site " + host + " with search word " + searchWord);
        }

    }

}
