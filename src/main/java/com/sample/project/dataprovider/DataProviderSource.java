package com.sample.project.dataprovider;

import com.google.common.io.Files;
import com.sample.project.dto.ProxyBrowserDto;
import com.sample.project.dto.ProxyDto;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlSuite;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sample.constants.CommonConsts.*;


public class DataProviderSource {
    @DataProvider(name = "proxyList")
    public static Object[][] getProxy(ITestContext context) throws IOException, JAXBException {
//        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(1);

        List<String> list = Files.readLines(new File("input/proxy.txt"), Charsets.UTF_8);

        List<ProxyBrowserDto> mainList = new ArrayList<>();
        for (String aList : list) {
            mainList.add(new ProxyBrowserDto(aList, firefoxWindowsAgent));
            mainList.add(new ProxyBrowserDto(aList, chromeWindows));
            mainList.add(new ProxyBrowserDto(aList, operaWindows));
            mainList.add(new ProxyBrowserDto(aList, IEWindows));
            mainList.add(new ProxyBrowserDto(aList, EdgeWindows));
        }

        Object[][] objArray = new Object[mainList.size()][];
        for (int i = 0; i < mainList.size(); i++) {
            objArray[i] = new Object[1];
            objArray[i][0] = mainList.get(i);
        }
        return objArray;
    }


    @DataProvider(name = "search", parallel = true)
    public static Object[][] getSearchWord(ITestContext context) throws IOException, JAXBException {
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(3);


        List<String> list = FileUtils.readLines(new File("input/search.txt"), Charsets.UTF_8);

        List<ProxyDto> proxyDtoList = new ArrayList<>();
        for (String string : list) {
            if (string.isEmpty()) {
                break;
            }
            List<String> stringList = Arrays.asList(string.split(","));
            proxyDtoList.add(new ProxyDto(stringList.get(0), stringList.get(1)));
        }


        Object[][] objArray = new Object[proxyDtoList.size()][];
        for (int i = 0; i < proxyDtoList.size(); i++) {
            objArray[i] = new Object[1];
            objArray[i][0] = proxyDtoList.get(i);
        }
        return objArray;
    }
}
