package com.vany.excleuploadsqlite.Excel;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.RequiresPermission;

import com.vany.excleuploadsqlite.db.DBConstants;

import org.apache.poi.hssf.usermodel.HSSFRow;
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
        Iterator<Row> rowIterator = null;
        try {
            fileInputStream = new FileInputStream(this.excelFile);
            System.out.println("Sheet " + this.excelFile);
            //Create Workbook instance holding reference to .xlsx file
            workbook = new HSSFWorkbook(fileInputStream);
            //Get first/desired sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            rowIterator = sheet.iterator();

            if (sheet.getPhysicalNumberOfRows() > 0) {
                for (int rowNumber = sheet.getFirstRowNum() + 1; rowNumber < sheet.getPhysicalNumberOfRows(); rowNumber++) {
                    Row row = sheet.getRow(rowNumber);
                    // check null.
                    if (row == null) {
                        continue;
                    }
                    insertRow(row);
//                    for (int cellNumber = row.getFirstCellNum(); cellNumber < row.getPhysicalNumberOfCells(); cellNumber++) {
//                        System.out.print(" / "+row.getCell(cellNumber).toString());
//                    }
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
        System.out.println("Row Data " + insertTableRow);
        dbConstants.insertData(
                insertTableRow.getCell(0).toString(),
                insertTableRow.getCell(1).toString(),
                insertTableRow.getCell(2).toString(),
                insertTableRow.getCell(3).toString(),
                insertTableRow.getCell(4).toString());
    }
}
