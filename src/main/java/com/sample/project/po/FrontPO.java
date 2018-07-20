package com.sample.project.po;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FrontPO extends BasePO {

    @FindBy(xpath = ".//*[@class='r']/a")
    private List<WebElement> results;

    @FindBy(xpath = ".//a")
    private List<WebElement> hrefs;

    public List<WebElement> getResults() {
        return results;
    }

    public List<WebElement> getHrefs() {
        return hrefs;
    }
}
