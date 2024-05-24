package com.mirea.kt.ribo.datastorageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName, etSpec;
    private CheckBox cbStudy;
    private DBManager dbManager;
    private ArrayList<Doctor> doctors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db",null,1));
        etName = findViewById(R.id.etName);
        etSpec = findViewById(R.id.etSpec);
        cbStudy = findViewById(R.id.cbStudy);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnNext = findViewById(R.id.btnNext);
        btnAdd.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        doctors = dbManager.loadAllDoctorsFromDatabase();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAdd){
            if(this.dbManager!=null){
                String name = etName.getText().toString();
                String spec = etSpec.getText().toString();
                Boolean isStudy = cbStudy.isChecked();
                if(!name.isEmpty() && !spec.isEmpty()){
                    boolean reesult = dbManager.saveDoctorToDatabase(new Doctor(name, spec, isStudy));
                    if (reesult){
                        Log.d("My_tag",name + " успешно добавлен в БД");
                        Toast.makeText(this, R.string.insert_succes, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this, R.string.insert_error, Toast.LENGTH_LONG).show();
                        Log.d("My_tag","Ошибка при добавлении новой записи в БД");
                    }
                }else{
                    Toast.makeText(this, R.string.incorrect_value, Toast.LENGTH_LONG).show();
                }
            }
        }else  if(v.getId() == R.id.btnNext){
            Log.d("My_tag","Переход в doctor activity");
            startActivity(new Intent(this, DoctorActivity.class));
        }
    }
}