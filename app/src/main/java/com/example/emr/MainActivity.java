package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button signin;
    EditText username,passwd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userName);
        passwd = findViewById(R.id.passwd);
        signin = findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String usrname = username.getText().toString();
            String pass = passwd.getText().toString();
            if(usrname.equals("") || pass.equals("")) {
                Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(usrname.equals("healthcare") && pass.equals("foobar")) {
                Intent navIntent = new Intent(MainActivity.this, navigation.class);
                startActivity(navIntent);
                return;
            }
            else{
                Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                return;
            }
            }
        });
    }
}