package com.sample.project.dataprovider;

import com.google.common.io.Files;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class DataProviderSource {

    @DataProvider(name = "webSite")
    public static Object[][] getFileEntry(ITestContext context) throws IOException, JAXBException {
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(1);

        List<String> list = Files.readLines(new File("input/proxy.txt"), Charsets.UTF_8);
        Object[][] objArray = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            objArray[i] = new Object[1];
            objArray[i][0] = list.get(i);
        }
        return objArray;
    }

    @DataProvider(name = "search")
    public static Object[][] getSearchWord(ITestContext context) throws IOException, JAXBException {
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(1);

        String string = FileUtils.readFileToString(new File("input/search.txt"), Charsets.UTF_8);
        List<String> list = Arrays.asList(string.split(","));

        Object[][] objArray = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            objArray[i] = new Object[1];
            objArray[i][0] = list.get(i);
        }
        return objArray;
    }
}
