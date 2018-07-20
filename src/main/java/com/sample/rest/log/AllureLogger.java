package com.sample.rest.log;

import com.sample.rest.utils.CustomUtils;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

public class AllureLogger {
    final static Logger logger = LoggerFactory.getLogger(AllureLogger.class);

    public static boolean logToslf4j;
    public static boolean logSoapCallToAllure;

    @Step(value = "{log}")
    public static void logInfo(String log) {
    }

    @Attachment(value = "{name}", type = "application/json")
    public static byte[] attachJsone(String name, ByteArrayOutputStream stream) {
        logToConsole(stream);
        return attach(stream);
    }

    @Attachment(value = "{name}", type = "application/xml")
    public static byte[] attachSoapCall(String name, ByteArrayOutputStream stream) {
        return attach(stream);
    }


    public static byte[] attach(ByteArrayOutputStream log) {
        byte[] array = log.toByteArray();
        log.reset();
        return array;
    }

    public static void logSQLResponseAsXML(String queryResponse) {
        ByteArrayOutputStream stream = CustomUtils.getByteArray(queryResponse);
        logToConsole(stream);
        AllureLogger.attachSoapCall("Log SQL Response as xml", stream);
    }

    public static void logSoapRequestAsXML(String queryResult) {
        ByteArrayOutputStream stream = CustomUtils.getByteArray(queryResult);
        logToConsole(stream);
        if (logSoapCallToAllure) {
            AllureLogger.attachSoapCall("Log Soap Request", stream);
        }
    }

    public static void logSoapRequestAsXML(String queryResult, Boolean logToConsole) {
        ByteArrayOutputStream stream = CustomUtils.getByteArray(queryResult);
        if (logToConsole) {
            logToConsole(stream);
        }
        if (logSoapCallToAllure) {
            AllureLogger.attachSoapCall("Log Soap Request", stream);
        }
    }

    public static void logSoapResponseAsXML(String queryResult) {
        logSoapAsXML("Log Soap Response", queryResult);
    }

    public static void logSoapAsXML(String stepName, String response) {
        ByteArrayOutputStream stream = CustomUtils.getByteArray(response);
        logToConsole(stream);
        if (logSoapCallToAllure) {
            AllureLogger.attachSoapCall(stepName, stream);
        }
    }

    public static void logSoapAsXML(String stepName, String response, boolean logToConsole) {
        ByteArrayOutputStream stream = CustomUtils.getByteArray(response);
        if (logToConsole) {
            logToConsole(stream);
        }
        if (logSoapCallToAllure) {
            AllureLogger.attachSoapCall(stepName, stream);
        }
    }


    private static void logToConsole(ByteArrayOutputStream stream) {
        if (logToslf4j) {
            logger.info("\n-------------\n" + stream.toString() + "\n");
        }
    }


}
