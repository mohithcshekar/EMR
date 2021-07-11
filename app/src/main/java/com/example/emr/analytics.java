package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

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


                String day1 = String.format("%02d",day);
                System.out.println("here---------"+year+"/"+month+"/"+day1);
                String[] cons_details=dbHandler.retrieveConsultationDate(year+"/"+month+"/"+day1);
                if(cons_details[0].equals("na")){
                    Toast.makeText(analytics.this, "Not found", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(analytics.this, "found", Toast.LENGTH_SHORT).show();
            }
        });

        analyticsDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                day = cldr.get(Calendar.DAY_OF_MONTH);
                month = cldr.get(Calendar.MONTH);
                year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(analytics.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                analyticsDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }
}