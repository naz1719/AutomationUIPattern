package com.sample.parsers.excel.bhv;

import com.sample.parsers.excel.core.BaseExcel;
import com.sample.rest.dto.etsy.Result;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

import static com.sample.parsers.excel.utils.CellStyleUtils.getCellSHeaderStyle;
import static com.sample.constants.CommonConsts.*;
import static com.sample.parsers.excel.utils.CellStyleUtils.getHyperLinkCellStyle;
import static com.sample.parsers.excel.utils.CellStyleUtils.getStandardCellStyle;

public class SampleBhv extends BaseBhv {
    private BaseExcel baseExcel = new BaseExcel(FILE_PATH).openFile();
    private Sheet sheet = baseExcel.getSheet(SHEET1);

    private CellStyle headerStyle = getCellSHeaderStyle(baseExcel);
    private CellStyle linkStyle = getHyperLinkCellStyle(baseExcel);

    private int BEGIN_ROW_CREATED_SHEET = 1;


    public void writeHeaders() {

        Row row = sheet.createRow(0);
        row.setHeightInPoints(60);

        for (int i = 0; i < SAMPLE_HEADERS.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(SAMPLE_HEADERS.get(i));
            cell.setCellStyle(headerStyle);
        }
        baseExcel.saveChangesToFile();
    }


    public void writeBodyOfCell(List<Result> list) {
        CellStyle cellStyle = getStandardCellStyle(baseExcel);
        for (int i = 0; i < list.size(); i++) {
            Row row1 = createCustomRow();
            writeCell(row1, 0, list.get(i).getUrl(), linkStyle);
            writeCell(row1, 1, String.valueOf(list.get(i).getNumFavorers()), cellStyle);
        }
        baseExcel.saveChangesToFile();
    }


    public Row createCustomRow() {
        return sheet.createRow(BEGIN_ROW_CREATED_SHEET++);
    }

    public Row getRow() {
        return sheet.createRow(BEGIN_ROW_CREATED_SHEET++);
    }

}
