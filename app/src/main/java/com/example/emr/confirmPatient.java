package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class confirmPatient extends AppCompatActivity {

    TextView confirmGen,confirmId;
    EditText confirmName,confirmAge,confirmHeight,confirmWeight;
    Button confirmNext;
    String patid;
    String nav="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_patient);

        patid = getIntent().getStringExtra("patid");
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
        String gender = getIntent().getStringExtra("gender");
        String height = getIntent().getStringExtra("height");
        String weight = getIntent().getStringExtra("weight");
        nav = getIntent().getStringExtra("nav");
        confirmName = findViewById(R.id.confirmName);
        confirmGen = findViewById(R.id.confirmGen);
        confirmAge = findViewById(R.id.confirmAge);
        confirmHeight = findViewById(R.id.confirmHeight);
        confirmWeight = findViewById(R.id.confirmWeight);
        confirmId = findViewById(R.id.confirmId);
        confirmNext = findViewById(R.id.confirmNext);
        confirmId.setText(patid);
        confirmName.setText(name);

        if(gender.equals("0"))
            confirmGen.setText("Male");
        else
            confirmGen.setText("Female");
        confirmAge.setText(age);
        confirmHeight.setText(height);
        confirmWeight.setText(weight);


        confirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = confirmName.getText().toString();
                String age1 = confirmAge.getText().toString();
                String weight1 = confirmWeight.getText().toString();
                String height1 = confirmHeight.getText().toString();

                if (!(name1.equals(name) && age1.equals(age) && weight1.equals(weight) && height1.equals(height))) {
                    DBHandler dbHandler = new DBHandler(confirmPatient.this);
                    dbHandler.updatePatient(patid,name1,age1,height1,weight1);
                    Toast.makeText(confirmPatient.this, "Updates are recorded", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(confirmPatient.this, "No changes are detected", Toast.LENGTH_SHORT).show();

                if(nav.equals("0")) {
                    Intent consultation_intent = new Intent(confirmPatient.this, consultation.class);

                    consultation_intent.putExtra("patid", patid);
                    startActivity(consultation_intent);
                }
                else{
                    Intent nav_intent = new Intent(confirmPatient.this, navigation.class);
                    startActivity(nav_intent);
                }

            }
        });

    }
}