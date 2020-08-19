package com.vany.excleuploadsqlite.Excel;

import android.content.Context;
import android.widget.Toast;
import com.vany.excleuploadsqlite.db.DBConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadExcel {

    private File excelFile;
    private Context context;
    private DBConstants dbConstants;


    ReadExcel(File filePath, DBConstants dbConstants, Context context) {
        this.excelFile = filePath;
        this.dbConstants = dbConstants;
        this.context = context;
    }

    public void readExcel() throws IOException {
        InputStream fileInputStream = null;
        Workbook workbook = null;
        try {
            fileInputStream = new FileInputStream(this.excelFile);
            System.out.println("Sheet " + this.excelFile);
            //Create Workbook instance holding reference to .xlsx file
            workbook = new HSSFWorkbook(fileInputStream);
            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                for (int rowNumber = sheet.getFirstRowNum() + 1; rowNumber < sheet.getPhysicalNumberOfRows(); rowNumber++) {
                    Row row = sheet.getRow(rowNumber);
                    // check null.
                    if (row == null) {
                        continue;
                    }
                    insertRow(row);
                }
            } else {
                Toast.makeText(this.context, "There is no records in Excel Sheet", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Toast.makeText(this.context, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println("Error : " + e.getMessage());
        } finally {
            if (workbook != null) {
                fileInputStream.close();
                workbook.close();
            }
        }
    }

    public void insertRow(Row insertTableRow) {
        dbConstants.insertData(
                insertTableRow.getCell(0).toString(),
                insertTableRow.getCell(1).toString(),
                insertTableRow.getCell(2).toString(),
                insertTableRow.getCell(3).toString(),
                insertTableRow.getCell(4).toString());
    }
}
