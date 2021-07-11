package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class patientRegForm extends AppCompatActivity {

    int gender=-1,age;
    private DBHandler dbHandler;
    Button fPReg;
    EditText fPName,fPAge,fPHeight,fPWeight,fPMob,fPAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_reg_form);

        fPName = findViewById(R.id.fPName);
        fPAge = findViewById(R.id.fPAge);
        fPHeight = findViewById(R.id.fPHeight);
        fPWeight = findViewById(R.id.fPWeight);
        fPMob = findViewById(R.id.fPMob);
        fPAdd = findViewById(R.id.fPAdd);
        fPReg = findViewById(R.id.fPReg);
        fPReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = fPName.getText().toString();
                try {
                    age = Integer.parseInt(fPAge.getText().toString());
                }
                catch (Exception e) {
                    age = -1;
                }
                String height = fPHeight.getText().toString();
                String weight = fPWeight.getText().toString();
                String mob = fPMob.getText().toString();
                String address = fPAdd.getText().toString();
                if(name.equals("")||age==-1||gender==-1||height.equals("")||weight.equals("")||mob.equals("")||address.equals("")){
                    Toast.makeText(patientRegForm.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    //Toast.makeText(patientRegForm.this, "values : "+name+" "+ age+" "+height+" "+weight+" "+mob+" "+address+" "+gender, Toast.LENGTH_SHORT).show();
                    dbHandler = new DBHandler(patientRegForm.this);
                    try {
                        dbHandler.addPatient(name, age, gender, height, weight, mob, address);
                        Toast.makeText(patientRegForm.this, "Registration success", Toast.LENGTH_SHORT).show();
                        Intent navPage = new Intent(patientRegForm.this, navigation.class);
                        startActivity(navPage);
                    }
                    catch (Exception e){
                        Toast.makeText(patientRegForm.this, "New registration failed: Incorrect info or patient may already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.maleRadio:
                if (checked)
                    gender=0;
                    break;
            case R.id.femaleRadio:
                if (checked)
                    gender=1;
                    break;
            default:gender=-1;
        }
    }
}