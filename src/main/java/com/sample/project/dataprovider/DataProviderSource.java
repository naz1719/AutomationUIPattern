package com.sample.project.dataprovider;

import com.google.common.io.Files;
import com.sample.project.dto.GeneralDto;
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

import static com.sample.constants.CommonConsts.*;


public class DataProviderSource {
//    @DataProvider(name = "proxyList", parallel = true)
//    public static Object[][] getProxy(ITestContext context) throws IOException, JAXBException {
////        context.getCurrentXmlTest().getSui    te().setDataProviderThreadCount(2);
//
//        List<String> list = Files.readLines(new File("input/proxy.txt"), Charsets.UTF_8);
//
//        List<ProxyBrowserDto> mainList = new ArrayList<>();
//        for (String aList : list) {
//            mainList.add(new ProxyBrowserDto(aList, chromeWindows));
//            mainList.add(new ProxyBrowserDto(aList, operaWindows));
//            mainList.add(new ProxyBrowserDto(aList, IEWindows));
//            mainList.add(new ProxyBrowserDto(aList, EdgeWindows));
//            mainList.add(new ProxyBrowserDto(aList, firefoxWindowsAgent));
//            mainList.add(new ProxyBrowserDto(aList, SafariMac));
//        }
//
//        Object[][] objArray = new Object[mainList.size()][];
//        for (int i = 0; i < mainList.size(); i++) {
//            objArray[i] = new Object[1];
//            objArray[i][0] = mainList.get(i);
//        }
//        return objArray;
//    }


    @DataProvider(name = "search", parallel = true)
    public static Object[][] getSearchWord(ITestContext context) throws IOException, JAXBException {
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(2);

        List<String> proxy = Files.readLines(new File("input/proxy.txt"), Charsets.UTF_8);
        List<String> searchList = FileUtils.readLines(new File("input/search.txt"), Charsets.UTF_8);


//      proxy
        List<ProxyBrowserDto> proxyList = new ArrayList<>();
        for (String aList : proxy) {
            proxyList.add(new ProxyBrowserDto(aList, chromeWindows));
            proxyList.add(new ProxyBrowserDto(aList, operaWindows));
            proxyList.add(new ProxyBrowserDto(aList, IEWindows));
            proxyList.add(new ProxyBrowserDto(aList, EdgeWindows));
            proxyList.add(new ProxyBrowserDto(aList, firefoxWindowsAgent));
            proxyList.add(new ProxyBrowserDto(aList, SafariMac));
        }


//      search
        List<ProxyDto> searchDtoList = new ArrayList<>();
        for (String string : searchList) {
            if (string.isEmpty()) {
                break;
            }
            List<String> stringList = Arrays.asList(string.split(","));
            searchDtoList.add(new ProxyDto(stringList.get(0), stringList.get(1)));
        }

//        All
        List<GeneralDto> dtoList = new ArrayList<>();

        for (ProxyBrowserDto proxyBrowserDto : proxyList) {
            for (ProxyDto proxyDto : searchDtoList) {
                dtoList.add(new GeneralDto(proxyBrowserDto,proxyDto));
            }
        }

        Object[][] objArray = new Object[dtoList.size()][];
        for (int i = 0; i < dtoList.size(); i++) {
            objArray[i] = new Object[1];
            objArray[i][0] = dtoList.get(i);
        }
        return objArray;
    }
}
