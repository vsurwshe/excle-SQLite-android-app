package com.vany.excleuploadsqlite.Excel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vany.excleuploadsqlite.R;

public class UploadExcel extends AppCompatActivity {
    public static final int PICKFILE_RESULT_CODE = 1;

    Button uploadButton;
    TextView filePathTextView;

    private Uri fileUri;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_excle);
        uploadButton = (Button) findViewById(R.id.ac_upload_excle_uplaod_button);
        filePathTextView = (TextView) findViewById(R.id.ac_upload_excel_upload_textView);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);
            }
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case PICKFILE_RESULT_CODE:
//                if (resultCode == -1) {
//                    fileUri = data.getData();
//                    filePath = fileUri.getPath();
//                    filePathTextView.setText(filePath);
//                }
//                break;
//        }
//    }
}