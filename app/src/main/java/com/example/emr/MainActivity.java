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
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Electronic Medical Records");
        username = findViewById(R.id.userName);
        passwd = findViewById(R.id.passwd);
        signin = findViewById(R.id.signin);
        dbHandler = new DBHandler(MainActivity.this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String usrname = username.getText().toString();
            String pass = passwd.getText().toString();
            String[] auth=dbHandler.authFetch();
            if(usrname.equals("") || pass.equals("")) {
                Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(usrname.equals(auth[0]) && pass.equals(auth[1])) {
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