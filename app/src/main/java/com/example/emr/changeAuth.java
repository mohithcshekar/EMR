package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changeAuth extends AppCompatActivity {

    EditText chUsername,chpass,chRePass;
    Button chCredBtn;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Electronic Medical Records");
        setContentView(R.layout.activity_change_auth);
        dbHandler = new DBHandler(changeAuth.this);
        chUsername=findViewById(R.id.chUsername);
        chpass=findViewById(R.id.chpass);
        chRePass=findViewById(R.id.chRePass);
        chCredBtn=findViewById(R.id.chCredBtn);
        chCredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrname=chUsername.getText().toString();
                String pass=chpass.getText().toString();
                String repass=chRePass.getText().toString();
                if(usrname.isEmpty()||pass.isEmpty()||repass.isEmpty()){
                    Toast.makeText(changeAuth.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(pass.equals(repass))){
                    Toast.makeText(changeAuth.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandler.authUpdate(usrname,pass);
                Toast.makeText(changeAuth.this, "Password set successfully", Toast.LENGTH_SHORT).show();
                Intent navigation=new Intent(changeAuth.this, navigation.class);
                startActivity(navigation);
            }
        });
    }
}