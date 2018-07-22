package com.sample.project.bo;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.project.po.FrontPO;

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
            frontPO.getResults().get(0).click();
            WebDriverManager.scrollDown();
        }
    }

}
