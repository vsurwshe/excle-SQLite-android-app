package com.vany.excleuploadsqlite.Excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Iterator;

public class ReadExcel {

    private File excelFile;

    ReadExcel(File filePath) {
        this.excelFile = filePath;
    }

    public Iterator<Row> readExcel() throws IOException {
        InputStream fileInputStream = null;
        Workbook workbook = null;
        Iterator<Row> rowIterator = null;
        try {
            fileInputStream = new FileInputStream(this.excelFile);
            System.out.println("Sheet " + fileInputStream + " " + this.excelFile);
            //Create Workbook instance holding reference to .xlsx file
            workbook = new HSSFWorkbook(fileInputStream);
            System.out.println("Sheet " + workbook);
            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println("Sheet " + sheet);
            //Iterate through each rows one by one
            rowIterator = sheet.iterator();
            System.out.println("Sheet " + sheet);
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
