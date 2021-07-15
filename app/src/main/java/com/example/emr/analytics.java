package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
    ArrayList <String> name_col,id_col,mob_col,consid_col,refby,remarks_col;
    customAdapter customAdapter;
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

                name_col = new ArrayList<>();
                id_col = new ArrayList<>();
                mob_col = new ArrayList<>();
                consid_col = new ArrayList<>();
                refby = new ArrayList<>();
                remarks_col = new ArrayList<>();

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
                Cursor cursor =dbHandler.retrieveConsultationDate(year+"/"+monthStr+"/"+dayStr);
                if(cursor.getCount()==0){
                    Toast.makeText(analytics.this, "Not found", Toast.LENGTH_SHORT).show();
                }
                else {

                    while (cursor.moveToNext()){
                        name_col.add(cursor.getString(0));
                        id_col.add(cursor.getString(1));
                        mob_col.add(cursor.getString(2));
                        consid_col.add(cursor.getString(3));
                        refby.add(cursor.getString(4));
                        remarks_col.add(cursor.getString(5));

                    }
                }
                customAdapter = new customAdapter(analytics.this,name_col,id_col,mob_col,consid_col,refby,remarks_col);
                recyclerView.

            }
        });

        analyticsDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                day = cldr.get(Calendar.DAY_OF_MONTH);
                month = cldr.get(Calendar.MONTH)+1;
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