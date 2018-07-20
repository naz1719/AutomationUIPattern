package com.sample.rest.steps;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelaySteps {

    private static final DelaySteps instance = new DelaySteps();
    final Logger logger = LoggerFactory.getLogger(DelaySteps.class);

    private DelaySteps() {
    }

    public static DelaySteps getInstance() {
        return instance;
    }

    @Step(value = "DelayStep: {seconds} seconds")
    public void delayStep(int seconds) throws InterruptedException {
        logger.info("[Start] DelayStep:" + seconds + " seconds");
        Thread.sleep(seconds * 1000);
    }

    @Step(value = "DelayStep: {delayDescription} : {seconds} seconds")
    public void delayStep(int seconds, String delayDescription) throws InterruptedException {
        logger.info("[Start] DelayStep:" + delayDescription + ": " + seconds + " seconds");
        Thread.sleep(seconds * 1000);
    }

}