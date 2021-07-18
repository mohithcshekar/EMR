package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class navigation extends AppCompatActivity {


    Button conNew,consul,updateRec,retRec,changeAuthBtn,logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        this.setTitle("Electronic Medical Records");
        conNew = findViewById(R.id.newReg);
        consul = findViewById(R.id.consul);
        updateRec = findViewById(R.id.updateRec);
        retRec = findViewById(R.id.retRec);
        changeAuthBtn=findViewById(R.id.changeAuthBtn);
        logOut=findViewById(R.id.logOut);
        conNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patReg = new Intent( navigation.this,patientRegForm.class);
                startActivity(patReg);
                return;
            }
        });

        consul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retPatPage = new Intent(navigation.this, retPat.class);
                retPatPage.putExtra("nav","0");
                startActivity(retPatPage);
            }
        });
        updateRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retPatPage = new Intent(navigation.this,retPat.class);
                retPatPage.putExtra("nav","1");
                startActivity(retPatPage);
            }
        });


        retRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent analytics_page = new Intent(navigation.this,analytics.class);
                startActivity(analytics_page);
            }
        });

        changeAuthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeauth = new Intent(navigation.this,changeAuth.class);
                startActivity(changeauth);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainactivity = new Intent(navigation.this,MainActivity.class);
                startActivity(mainactivity);
            }
        });
    }
}