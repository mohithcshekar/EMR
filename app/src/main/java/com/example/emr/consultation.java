package com.example.emr;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class consultation extends AppCompatActivity {


    Button consfinish;
    EditText consRefby,consDiag,consMedMor,consMedAft,consMedNight,consRemarks;
    FloatingActionButton prevCons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        this.setTitle("Electronic Medical Records");
        String patid =getIntent().getStringExtra("patid");
        consfinish=findViewById(R.id.consFinish);
        consRefby=findViewById(R.id.consRefby);
        consDiag=findViewById(R.id.consDiag);
        consMedMor=findViewById(R.id.consMedMor);
        consMedAft=findViewById(R.id.consMedAft);
        consMedNight=findViewById(R.id.consMedNight);
        consRemarks=findViewById(R.id.consRemarks);
        prevCons=findViewById(R.id.prevCons);
        DBHandler dbHandler= new DBHandler(consultation.this);
        consfinish.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String refby = consRefby.getText().toString();
                String diagnoise = consDiag.getText().toString();
                String medmor = consMedMor.getText().toString();
                String medaft = consMedAft.getText().toString();
                String mednight = consMedNight.getText().toString();
                String remarks = consRemarks.getText().toString();
                if(refby.isEmpty()||diagnoise.isEmpty()||medmor.isEmpty()||medaft.isEmpty()||mednight.isEmpty()||remarks.isEmpty()){
                    Toast.makeText(consultation.this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandler.addConsultation(refby,patid,diagnoise,medmor,medaft,mednight,remarks);
                Toast.makeText(consultation.this, "Consultation sucessfull", Toast.LENGTH_SHORT).show();
                Intent navPage = new Intent(consultation.this, navigation.class);
                startActivity(navPage);
            }
        });
        prevCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  showresult_intent =new Intent(consultation.this,showResult.class);
                showresult_intent.putExtra("date_id",(patid));
                showresult_intent.putExtra("dateFlag","3");
                startActivity(showresult_intent);
            }
        });

    }
}