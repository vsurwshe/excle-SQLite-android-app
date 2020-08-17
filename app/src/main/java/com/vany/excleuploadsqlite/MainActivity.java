package com.vany.excleuploadsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vany.excleuploadsqlite.Excle.UploadExcle;

public class MainActivity extends AppCompatActivity {

    Button enterApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterApp = (Button) findViewById(R.id.enterApp);
        enterApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You enter dashboard successfully. ",Toast.LENGTH_LONG).show();
                Intent excleMainItnet= new Intent(MainActivity.this, Dashborad.class);
                startActivity(excleMainItnet);
            }
        });
    }
}