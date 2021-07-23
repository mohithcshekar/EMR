package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.String.valueOf;

public class analytics extends AppCompatActivity {


    Button dateBtn,patIdBtn,consIdBtn,backBtn;
    EditText analyticsPatId,analyticsConsId,analyticsDate;
    DatePickerDialog picker;
    private DBHandler dbHandler;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        this.setTitle("Electronic Medical Records");
        dateBtn=findViewById(R.id.dateBtn);
        patIdBtn=findViewById(R.id.patIdBtn);
        consIdBtn=findViewById(R.id.consIdBtn);
        backBtn=findViewById(R.id.backBtn);

        analyticsPatId=findViewById(R.id.analyticsPatId);
        analyticsConsId=findViewById(R.id.analyticsConsId);
        analyticsDate=findViewById(R.id.analyticsDate);
        dbHandler = new DBHandler(analytics.this);

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String monthStr,dayStr;
                if (1<=day && day<=9)
                    dayStr="0"+day;
                else
                    dayStr=valueOf(day);
                if(month!=10 && month!=11 && month!=12)
                    monthStr="0"+month;
                else
                    monthStr=valueOf(month);
                System.out.println("here---------"+year+"/"+monthStr+"/"+dayStr);
                if(year==0){
                    Toast.makeText(analytics.this, "Date is necessary to check records", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent  showresult_intent =new Intent(analytics.this,showResult.class);
                showresult_intent.putExtra("date_id",(year+"/"+monthStr+"/"+dayStr));
                showresult_intent.putExtra("dateFlag","1");
                startActivity(showresult_intent);

            }
        });

        analyticsDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                day = cldr.get(Calendar.DAY_OF_MONTH);
                month = cldr.get(Calendar.MONTH);
                year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(analytics.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {
                                day=dayOfMonth;
                                month=monthOfYear+1;
                                year=year1;
                                analyticsDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        patIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fetchPatId=analyticsPatId.getText().toString();
                if(fetchPatId.isEmpty()){
                    Toast.makeText(analytics.this, "Patiend ID necessary", Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] patient_details = dbHandler.retrievePatientId(Integer.parseInt(fetchPatId));
                if(patient_details[0]=="na"){
                    Toast.makeText(analytics.this, "Patient details not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Intent confirmPat = new Intent(analytics.this, confirmPatient.class);
                    confirmPat.putExtra("patid", patient_details[0]);
                    confirmPat.putExtra("name", patient_details[1]);
                    confirmPat.putExtra("age", patient_details[2]);
                    confirmPat.putExtra("gender", patient_details[3]);
                    confirmPat.putExtra("height", patient_details[4]);
                    confirmPat.putExtra("weight", patient_details[5]);
                    confirmPat.putExtra("phno", patient_details[6]);
                    confirmPat.putExtra("nav", "1");
                    startActivity(confirmPat);

                }
            }
        });
        consIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fetchConsId=analyticsConsId.getText().toString();
                if(fetchConsId.isEmpty()){
                    Toast.makeText(analytics.this, "Consultation ID necessary", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent  showresult_intent =new Intent(analytics.this,showResult.class);
                showresult_intent.putExtra("date_id",(fetchConsId));
                showresult_intent.putExtra("dateFlag","2");
                startActivity(showresult_intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navigation = new Intent(analytics.this, navigation.class);
                startActivity(navigation);
            }
        });
    }
}