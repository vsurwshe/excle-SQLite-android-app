package com.vany.excleuploadsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vany.excleuploadsqlite.Excel.ExportExcel;
import com.vany.excleuploadsqlite.Excel.UploadExcel;

public class Dashborad extends AppCompatActivity {

    Button uplaodButton;
    Button exportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashborad);
        uplaodButton = (Button) findViewById(R.id.upload_button);
        exportButton =(Button) findViewById(R.id.export_button);
        // this method will open the upload excle component
        uplaodButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashborad.this, "You enter into upload excle componet",Toast.LENGTH_LONG).show();
                Intent uploadIntent = new Intent(Dashborad.this, UploadExcel.class);
                startActivity(uploadIntent);
            }
        }));
        // this will open the export excle component
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashborad.this,"You enter into export excle compoent",Toast.LENGTH_LONG).show();
                Intent exportIntent = new Intent(Dashborad.this, ExportExcel.class);
                startActivity(exportIntent);
            }
        });
    }
}