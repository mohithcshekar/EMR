package com.example.emr;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "emrdb";
    private static final int DB_VERSION = 1;


    public DBHandler(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String patient_table_creation = "CREATE TABLE PATIENT (  ID_COL INTEGER PRIMARY KEY AUTOINCREMENT,  NAME_COL TEXT,AGE_COL INTEGER, GENDER_COL INTEGER, HEIGHT_COL TEXT,WEIGHT_COL TEXT,MOBILE_COL TEXT UNIQUE, ADDRESS_COL TEXT)";
        db.execSQL(patient_table_creation);
        String consultation_table_creation="CREATE TABLE CONSULTATION (CONSID_COL INTEGER PRIMARY KEY AUTOINCREMENT, TIME_COL TEXT, ID_COL INTEGER,REFBY TEXT, DIAGONISE_COL TEXT,MED_MORN_COL TEXT,MED_AFT_COL TEXT,MED_NIGHT_COL TEXT, REMARK_COL TEXT,  FOREIGN KEY (ID_COL) REFERENCES PATIENT (ID_COL))";
        db.execSQL(consultation_table_creation);

        db.execSQL("UPDATE sqlite_sequence SET seq = 777 WHERE name = 'CONSULTATION'");

        db.execSQL("INSERT INTO sqlite_sequence (name,seq) SELECT 'CONSULTATION', 777 WHERE NOT EXISTS (SELECT changes() AS change FROM sqlite_sequence WHERE change <> 0)");

        String authentication_table_creation = "CREATE TABLE AUTHENTICATION (  USERNAME STRING PRIMARY KEY , PASSWORD STRING )";
        db.execSQL(authentication_table_creation);
        db.execSQL("INSERT INTO AUTHENTICATION values(?,?)",new String[]{"healthcare","foobar"});

    }

    public String[] authFetch() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] auth = new String[2];
        Cursor cursor = db.rawQuery("SELECT * FROM AUTHENTICATION ",null);
        if (cursor.moveToFirst()) {
            auth[0] = cursor.getString(0);
            auth[1] = cursor.getString(1);
        }
        cursor.close();
        return auth;
    }
    public void authUpdate(String usrName, String passwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM AUTHENTICATION");
        db.execSQL("INSERT INTO AUTHENTICATION values(?,?)",new String[]{usrName,passwd});
    }
    public void addPatient(String name,int age,int gender,String height,String weight,String mob,String address) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(ID_COL, id);
        values.put("NAME_COL", name);
        values.put("AGE_COL", age);
        values.put("GENDER_COL", gender);
        values.put("HEIGHT_COL", height);
        values.put("WEIGHT_COL", weight);
        values.put("MOBILE_COL", mob);
        values.put("ADDRESS_COL", address);
        db.insert("PATIENT", null, values);
        db.close();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addConsultation(String refby, String id,String diagnoise, String medmor, String medaft, String mednight, String remarks) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("CONSID_COL", name);
        values.put("TIME_COL", dtf.format(now));
        values.put("ID_COL", id);
        values.put("REFBY", refby);
        values.put("DIAGONISE_COL", diagnoise);
        values.put("MED_MORN_COL", medmor);
        values.put("MED_AFT_COL", medaft);
        values.put("MED_NIGHT_COL", mednight);
        values.put("REMARK_COL", remarks);

        db.insert("CONSULTATION", null, values);
        db.close();
    }

    public String[] retrievePatientId(int patid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] patient_details = new String[7];
        Cursor cursor = db.rawQuery("SELECT * FROM PATIENT WHERE ID_COL = "+patid,null);
        if (cursor.moveToFirst()) {
            patient_details[0]=cursor.getString(0);
            patient_details[1]=cursor.getString(1);
            patient_details[2]=cursor.getString(2);
            patient_details[3]=cursor.getString(3);
            patient_details[4]=cursor.getString(4);
            patient_details[5]=cursor.getString(5);
            patient_details[6]=cursor.getString(6);
        }
        else{
            patient_details[0]="na";
        }
        cursor.close();
        db.close();
        return patient_details;
    }
    public String[] retrievePatientMob(String patMob)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] patient_details = new String[7];
        Cursor cursor = db.rawQuery("SELECT * FROM PATIENT WHERE MOBILE_COL = "+patMob,null);
        if (cursor.moveToFirst()) {
            patient_details[0]=cursor.getString(0);
            patient_details[1]=cursor.getString(1);
            patient_details[2]=cursor.getString(2);
            patient_details[3]=cursor.getString(3);
            patient_details[4]=cursor.getString(4);
            patient_details[5]=cursor.getString(5);
            patient_details[6]=cursor.getString(6);
        }
        else{
            patient_details[0]="na";
        }
        cursor.close();
        db.close();
        return patient_details;
    }

    public Cursor retrieveConsultationDate(String date)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cons_details = new String[9];
        Cursor cursor = db.rawQuery("SELECT P.NAME_COL,P.ID_COL,P.MOBILE_COL,C.CONSID_COL,C.REFBY,C.REMARK_COL  FROM PATIENT P NATURAL JOIN CONSULTATION C where  substr(TIME_COL,1,10)=?",new String[]{date});
        return cursor;
    }
    public Cursor retrieveConsultationPat(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cons_details = new String[9];
        Cursor cursor = db.rawQuery("SELECT P.NAME_COL,P.ID_COL,P.MOBILE_COL,C.CONSID_COL,C.REFBY,C.REMARK_COL  FROM PATIENT P NATURAL JOIN CONSULTATION C where P.ID_COL=?",new String[]{id});
        return cursor;
    }
    public Cursor retrieveConsultationId(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cons_details = new String[9];
        Cursor cursor = db.rawQuery("SELECT P.NAME_COL,P.ID_COL,P.MOBILE_COL,C.CONSID_COL,C.REFBY,C.REMARK_COL  FROM PATIENT P NATURAL JOIN CONSULTATION C where  C.CONSID_COL=?",new String[]{id});
        return cursor;
    }
    public void updatePatient(String patId, String name, String age, String height,String weight){
        SQLiteDatabase db = this.getWritableDatabase();
        //NAME_COL TEXT,AGE_COL INTEGER, GENDER_COL INTEGER, HEIGHT_COL TEXT,WEIGHT_COL TEXT
        //db.execSQL("UPDATE PATIENT SET NAME_COL = " + name+ " , AGE_COL = "+age+" , HEIGHT_COL = "+height+" , WEIGHT_COL = "+weight+"  WHERE ID_COL = "+patId);


        ContentValues cv = new ContentValues();
        cv.put("NAME_COL",name);
        cv.put("AGE_COL", age);
        cv.put("HEIGHT_COL",height);
        cv.put("WEIGHT_COL",weight);
        db.update("PATIENT", cv, "ID_COL=?", new String[]{patId});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        List<String> tables = new ArrayList<>();
        while (c.moveToNext()) {
            tables.add(c.getString(0));
        }
        for (String table : tables) {
            String dropQuery = "DROP TABLE IF EXISTS " + table;
            db.execSQL(dropQuery);
        }
        c.close();
    }
}
