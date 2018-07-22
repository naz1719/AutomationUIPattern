package com.sample.project.bo;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.core.utils.WaitManager;
import com.sample.project.po.FrontPO;
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

    public void logic() {
        if (frontPO.getResults().size() > 0) {
            WebElement element = frontPO.getResults().get(0);
            waitManager.fluentElementWait(element);
            WebDriverManager.clickByJS(element);
        }
        WaitManager.sleepTimeOut(5000);
        WebDriverManager.scrollDown();
        WaitManager.sleepTimeOut(2000);
        WebDriverManager.scrollUp();
        String url = WebDriverManager.getDriver().getCurrentUrl();
//        List<WebElement> webElementList = WebDriverManager.getDriver().findElements(By.xpath(".//a[contains(@href,'" + url + "')]"));
        List<WebElement> webElementList = WebDriverManager.getDriver().findElements(By.xpath(".//a"));
        if (webElementList.size() > 0) {
            WebElement element = webElementList.get(0);
            waitManager.fluentElementWait(element);
            WebDriverManager.clickByJS(element);
        }
        WebDriverManager.scrollDown();
        WebDriverManager.getDriver().navigate().back();
        WaitManager.sleepTimeOut(5000);
        WebDriverManager.refresh();
    }

}
