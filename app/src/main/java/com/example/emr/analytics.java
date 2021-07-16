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
                Intent  showresult_intent =new Intent(analytics.this,showResult.class);
                showresult_intent.putExtra("date",(year+"/"+monthStr+"/"+dayStr));
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
                // date picker dialog
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
    }
}