package com.vany.excleuploadsqlite.Excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ReadExcel {

    private String filePath;

    ReadExcel(String filePath) {
        this.filePath = filePath;
    }

    public Iterator<Row> readExcel() throws IOException {
        InputStream fileInputStream = null;
        Workbook workbook = null;
        Iterator<Row> rowIterator = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            //Create Workbook instance holding reference to .xlsx file
            workbook = new HSSFWorkbook(fileInputStream);
            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            rowIterator = sheet.iterator();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                fileInputStream.close();
                workbook.close();
            }
        }
        return rowIterator;
    }
}
