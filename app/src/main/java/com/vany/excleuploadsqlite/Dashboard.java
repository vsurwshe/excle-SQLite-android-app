package com.vany.excleuploadsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vany.excleuploadsqlite.Excel.ExportExcel;
import com.vany.excleuploadsqlite.Excel.UploadExcel;
import com.vany.excleuploadsqlite.db.DBConstants;
import com.vany.excleuploadsqlite.helper.CustomerAdpater;
import com.vany.excleuploadsqlite.pojo.Customer;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    Button uploadButton;
    Button exportButton;
    List<Customer> myCustomerList;
    RecyclerView custRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashborad);
        uploadButton = (Button) findViewById(R.id.upload_button);
        exportButton = (Button) findViewById(R.id.export_button);
        custRecycleView = findViewById(R.id.dash_recycle_view);

        myCustomerList = new ArrayList<Customer>();
        this.LoadData();
        // this method will open the upload excel component
        uploadButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpload();
            }
        }));
        // this will open the export excel component
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExport();
            }
        });
    }

    public void openExport() {
        Toast.makeText(Dashboard.this, "You enter into export excle compoent", Toast.LENGTH_LONG).show();
        Intent exportIntent = new Intent(Dashboard.this, ExportExcel.class);
        startActivity(exportIntent);
    }

    public void openUpload() {
        Toast.makeText(Dashboard.this, "You enter into upload excle componet", Toast.LENGTH_LONG).show();
        Intent uploadIntent = new Intent(Dashboard.this, UploadExcel.class);
        startActivity(uploadIntent);
    }

    public void LoadData() {
        DBConstants dbConstants = null;
        try {
            dbConstants = new DBConstants(this);
            dbConstants.open();
            Cursor listOfCustomer = dbConstants.selectData();
            while (listOfCustomer.moveToNext()) {
                myCustomerList.add(new Customer(
                        listOfCustomer.getInt(listOfCustomer.getColumnIndex(DBConstants.CUSTOMER_ID)),
                        listOfCustomer.getString(listOfCustomer.getColumnIndex(DBConstants.CUSTOMER_NAME)),
                        listOfCustomer.getString(listOfCustomer.getColumnIndex(DBConstants.CUSTOMER_CONTACT_NO)),
                        listOfCustomer.getString(listOfCustomer.getColumnIndex(DBConstants.CUSTOMER_EMAIL)),
                        listOfCustomer.getString(listOfCustomer.getColumnIndex(DBConstants.CUSTOMER_ADDRESS)),
                        listOfCustomer.getString(listOfCustomer.getColumnIndex(DBConstants.CUSTOMER_FEEDBACK))
                ));
            }
            CustomerAdpater myCustomerAdapter = new CustomerAdpater(this, myCustomerList);
            custRecycleView.setAdapter(myCustomerAdapter);
            custRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            System.out.println("Customer " + myCustomerList);
        } catch (Exception exp) {
            Toast.makeText(Dashboard.this, "" + exp.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (dbConstants != null) {
                dbConstants.close();
            }
        }
    }
}