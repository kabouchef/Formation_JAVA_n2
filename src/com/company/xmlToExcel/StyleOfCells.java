package com.company.xmlToExcel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormat;

public class StyleOfCells {

    public void styleEuroBorder(){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet spreadSheet = wb.createSheet("General");
        HSSFCellStyle styleEuroBorder = wb.createCellStyle();
        DataFormat df = wb.createDataFormat();
        styleEuroBorder.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        styleEuroBorder.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        styleEuroBorder.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        styleEuroBorder.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        styleEuroBorder.setDataFormat(df.getFormat("#,##0.00 €"));
    }
}
