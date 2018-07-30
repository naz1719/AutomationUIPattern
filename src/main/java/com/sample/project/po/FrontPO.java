package com.sample.project.po;


import com.sample.core.core.driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FrontPO extends BasePO {

    @FindBy(xpath = ".//*[@id='ip']")
    private WebElement ip;

    @FindBy(xpath = ".//*[@class='rc-anchor rc-anchor-normal rc-anchor-light']")
    private List<WebElement> captcha;

    public List<WebElement> getCaptcha() {
        return captcha;
    }

    //    @FindBy(xpath = ".//a")
//    private List<WebElement> hrefs;

    public List<WebElement> getResults(String s) {
        return WebDriverManager.getDriver().findElements(By.xpath(".//*[@class='r']/a[contains(@href,'" + s + "')]"));
    }

    public String getMyIp() {
        return ip.getText();
    }

}
