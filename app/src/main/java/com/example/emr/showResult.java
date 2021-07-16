package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class showResult extends AppCompatActivity {


    ArrayList<String> name_col,id_col,mob_col,consid_col,refby,remarks_col;
    customAdapter customAdapter;
    private DBHandler dbHandler;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        dbHandler = new DBHandler(showResult.this);
        String date = getIntent().getStringExtra("date");

        name_col = new ArrayList<>();
        id_col = new ArrayList<>();
        mob_col = new ArrayList<>();
        consid_col = new ArrayList<>();
        refby = new ArrayList<>();
        remarks_col = new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        Cursor cursor =dbHandler.retrieveConsultationDate(date);
        if(cursor.getCount()==0){
            Toast.makeText(showResult.this, "Not found", Toast.LENGTH_SHORT).show();
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
        customAdapter = new customAdapter(showResult.this,name_col,id_col,mob_col,consid_col,refby,remarks_col);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(showResult.this));
    }
}