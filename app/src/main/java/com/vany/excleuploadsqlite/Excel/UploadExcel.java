package com.vany.excleuploadsqlite.Excel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vany.excleuploadsqlite.R;
import com.vany.excleuploadsqlite.db.DBConstants;

import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

public class UploadExcel extends AppCompatActivity {
    public static final int PICKFILE_RESULT_CODE = 1;

    Button uploadButton;
    TextView filePathTextView;
    private Uri fileUri;
    private String filePath;


    private static final String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.INTERNET};

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_excle);
        uploadButton = (Button) findViewById(R.id.ac_upload_excle_uplaod_button);
        filePathTextView = (TextView) findViewById(R.id.ac_upload_fileUrl);
        ActivityCompat.requestPermissions(UploadExcel.this, PERMISSIONS, 112);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasPermissions(UploadExcel.this, PERMISSIONS)) {
                    Toast t = Toast.makeText(getApplicationContext(), "You don't have read access !", Toast.LENGTH_LONG);
                    t.show();
                } else {
                    showFileChooser();
                }
            }
        });
    }

    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/vnd.ms-excel");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), PICKFILE_RESULT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == -1) {

                    try {
                        fileUri = data.getData();
                        File excelFilePath = new File(getRealPathFromURI(data.getData()));
                        filePathTextView.setVisibility(1);
                        filePathTextView.setText(excelFilePath.toString());
                        DBConstants dbConstants = new DBConstants(this);
                        dbConstants.open();
                        ReadExcel readExcelObject = new ReadExcel(excelFilePath, dbConstants, UploadExcel.this);
                        readExcelObject.readExcel();
                        dbConstants.close();

                    } catch (IOException e) {
                        Toast.makeText(UploadExcel.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();

                    } catch (Exception exp) {
                        Toast.makeText(UploadExcel.this, "" + exp.getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println("Error: " + exp.getMessage());
                    }
                    break;
                }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}