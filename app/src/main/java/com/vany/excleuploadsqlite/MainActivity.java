package com.vany.excleuploadsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button enterApp;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.main_usrename);
        password = (EditText) findViewById(R.id.main_user_password);
        enterApp = (Button) findViewById(R.id.enterApp);
        enterApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void userLogin(String username, String password) {
        if (username.equals("Admin") && password.equals("123456")) {
            Toast.makeText(MainActivity.this, "You enter dashboard successfully. ", Toast.LENGTH_LONG).show();
            Intent dashboard = new Intent(MainActivity.this, Dashborad.class);
            startActivity(dashboard);
        } else {
            Toast.makeText(MainActivity.this, "Sorry your correctional is wrong", Toast.LENGTH_LONG).show();
        }
    }
}