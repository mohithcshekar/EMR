package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class retPat extends AppCompatActivity {

    Button fetchNext;
    EditText fetchPatId,fetchPatMob;
    private DBHandler dbHandler;
    String nav = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ret_pat);

        fetchNext=findViewById(R.id.fetchNext);
        fetchPatId=findViewById(R.id.fetchPatId);
        fetchPatMob=findViewById(R.id.fetchPatMob);
        dbHandler = new DBHandler(retPat.this);

        nav = getIntent().getStringExtra("nav");
        fetchNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patId=fetchPatId.getText().toString();
                String patMob=fetchPatMob.getText().toString();

                if(patId.equals("") && patMob.equals("")){
                    Toast.makeText(retPat.this, "Provide any of the field to retrieve patient", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(patMob.equals("")){
                    String[] patient_details = dbHandler.retrievePatientId(Integer.parseInt(patId));
                    if(patient_details[0]=="na"){
                        Toast.makeText(retPat.this, "Patient details not found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        Intent confirmPat = new Intent(retPat.this, confirmPatient.class);
                        confirmPat.putExtra("patid", patient_details[0]);
                        confirmPat.putExtra("name", patient_details[1]);
                        confirmPat.putExtra("age", patient_details[2]);
                        confirmPat.putExtra("gender", patient_details[3]);
                        confirmPat.putExtra("height", patient_details[4]);
                        confirmPat.putExtra("weight", patient_details[5]);
                        confirmPat.putExtra("nav", nav);
                        startActivity(confirmPat);

                    }

                }
                else{
                    String[] patient_details = dbHandler.retrievePatientMob(patMob);
                    if(patient_details[0]=="na"){
                        Toast.makeText(retPat.this, "Patient details not found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        Intent confirmPat = new Intent(retPat.this, confirmPatient.class);
                        confirmPat.putExtra("patid", patient_details[0]);
                        confirmPat.putExtra("name", patient_details[1]);
                        confirmPat.putExtra("age", patient_details[2]);
                        confirmPat.putExtra("gender", patient_details[3]);
                        confirmPat.putExtra("height", patient_details[4]);
                        confirmPat.putExtra("weight", patient_details[5]);
                        confirmPat.putExtra("nav", nav);
                        startActivity(confirmPat);

                    }
                }

            }
        });
    }
}