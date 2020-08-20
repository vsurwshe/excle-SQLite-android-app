package com.vany.excleuploadsqlite.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.vany.excleuploadsqlite.R;
import com.vany.excleuploadsqlite.pojo.Customer;

public class ViewDetails extends AppCompatActivity {

    TextView cust_name, cust_email, cust_contact, cust_address;
    Customer tempCustomerObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        cust_name = findViewById(R.id.ac_view_name);
        cust_email = findViewById(R.id.ac_view_email);
        cust_contact = findViewById(R.id.ac_view_contact);
        cust_address = findViewById(R.id.ac_view_address);
        Intent tempIntent = getIntent();
        this.tempCustomerObject = (Customer) tempIntent.getSerializableExtra("Customer");
        System.out.println("Customer " + this.tempCustomerObject);
        LoadCustomerDetails();
    }

    public void LoadCustomerDetails() {
        cust_name.setText(tempCustomerObject.getCuName());
        cust_email.setText(tempCustomerObject.getCuEmail());
        cust_contact.setText(tempCustomerObject.getCuContactNumber());
        cust_address.setText(tempCustomerObject.getCuAddress());
    }
}