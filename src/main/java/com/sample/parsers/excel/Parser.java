package com.sample.parsers.excel;


import com.sample.parsers.excel.core.BaseExcel;
import com.sample.parsers.excel.model.excel.ExcelModelTitleProcessor;
import com.sample.parsers.excel.model.excel.ProductEx;
import com.sample.parsers.excel.model.payloads.ProductList;

import java.util.List;

import static com.sample.constants.CommonConsts.FILE_PATH;
import static com.sample.constants.CommonConsts.SAMPLE_SHEET;

public class Parser {

    private BaseExcel baseExcel = new BaseExcel(FILE_PATH).openFile();

    public List<ProductList> retrieveAllProductsWithTitles() {
        List<ProductEx> products = baseExcel.unmarshal(ProductEx.class, SAMPLE_SHEET, 6);
        return ExcelModelTitleProcessor.processAllProducts(products);
    }
}
