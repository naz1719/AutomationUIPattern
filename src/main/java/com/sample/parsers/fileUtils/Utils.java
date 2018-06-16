package com.sample.parsers.fileUtils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sample.constants.CommonConsts.FOLDER_PATH;

public class Utils {

    public static boolean isStream(String input) {
        final Pattern pattern = Pattern.compile("([a-zA-Z0-9]+)-([a-zA-Z0-9]+)");
        final Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean getExcelFilesNames(String input) {
        final Pattern pattern = Pattern.compile("(\\w)+(.(xlsx|xlx|xls))");
        final Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static String getExcelPath() {
        final File folder = new File(FOLDER_PATH);
        List<String> list = listFilesForFolder(folder);

        return list.get(0);
    }

    public static List<String> listFilesForFolder(final File folder) {
        List<String> list = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if (getExcelFilesNames(fileEntry.getName())) {
                    list.add(fileEntry.getName());
                }
            }
        }
        return list;
    }

    public static String getFirstPartOfStream(String input) {
        final Pattern pattern = Pattern.compile("([a-zA-Z0-9]+)-([a-zA-Z0-9]+)");
        final Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return input;
    }

    public static void copyFileUsingApacheCommonsIO(String source, String dest) {
        try {
            FileUtils.copyFileToDirectory(new File(source), new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String filterUrl(String input) {
        String string = null;

        Pattern pattern = Pattern.compile("in/([a-zA-Z0-9]+)-([a-zA-Z0-9]+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            string = matcher.group(1) + "-" + matcher.group(2);
        } else {
            Pattern pattern1 = Pattern.compile("in/([a-zA-Z0-9]+)");
            Matcher matcher1 = pattern1.matcher(input);
            if (matcher1.find()) {
                string = matcher1.group(1);
            }
        }
        return string;
    }

    public static String filterNameUrl(String input) {
        String string = null;

//        Pattern pattern = Pattern.compile("in/([a-zA-Z0-9]+)");
        Pattern pattern = Pattern.compile("in/(.*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            string = matcher.group(1);
        }
        return string;
    }

    public static void createHtmlFile(String string, final String path, String source) {

        try {
            File path1 = new File(path + string + ".html");
            FileUtils.writeStringToFile(path1, source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//      String str = a.replaceAll("[<|>|:|\"|/|\\\\|\\||?]|\\*", "_");
//                createHtmlFile(str, "output/" + group + "/", sourceHtml);

//    public void isMoreThanZero() {
//        boolean var = true;
//        int moreSize = pageBO.getMore().size();
//        long endTime = System.currentTimeMillis();
//        long duration = (endTime - startTime);
//
//        if (duration > 9000) {
//            var = false;
//        }
//        if (moreSize > 0) {
//            WebElement webElement = pageBO.getMore().get(0);
//            WebDriverManager.executeScript("arguments[0].click();", webElement);
//            if (var) {
//                isMoreThanZero();
//            }
//        }
//
//    }
}