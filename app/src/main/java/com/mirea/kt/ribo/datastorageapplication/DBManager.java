package com.mirea.kt.ribo.datastorageapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Currency;

public class DBManager {
    private SQLiteOpenHelper sqLiteHelper;
    public DBManager(SQLiteOpenHelper sqLiteHelper) {
        this.sqLiteHelper = sqLiteHelper;
    }
    public boolean saveDoctorToDatabase(Doctor doctor){
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", doctor.getName());
        cv.put("spec", doctor.getSpec());
        if (doctor.isStudy()){
            cv.put("study", 1);
        }else{
            cv.put("study", 0);
        }
        long rowId = db.insert("TABLE_DOCTORS",null,cv);
        cv.clear();
        db.close();
        return rowId != -1;
    }
    public ArrayList<Doctor> loadAllDoctorsFromDatabase(){
        ArrayList<Doctor> doctors = new ArrayList<>();
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        Cursor dbCursor = db.query("TABLE_DOCTORS", null, null, null, null, null, null);
        if(dbCursor.moveToFirst()){
            do {
                String name = dbCursor.getString(dbCursor.getColumnIndexOrThrow("name"));
                String spec = dbCursor.getString(dbCursor.getColumnIndexOrThrow("spec"));
                int study = dbCursor.getInt(dbCursor.getColumnIndexOrThrow("study"));
                boolean isStudy = false;
                if (study==1) {
                    isStudy = true;
                }
                doctors.add(new Doctor(name, spec, isStudy));
            }while (dbCursor.moveToNext());
        }
        dbCursor.close();
        db.close();
        return doctors;
    }
}
