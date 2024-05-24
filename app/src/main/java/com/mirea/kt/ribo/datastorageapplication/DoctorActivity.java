package com.mirea.kt.ribo.datastorageapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DoctorActivity extends AppCompatActivity {
    private DBManager dbManager;
    private RecyclerView rcView;;
    private DoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        this.dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db",null,1));
        ArrayList<Doctor> doctors = dbManager.loadAllDoctorsFromDatabase();
        adapter = new DoctorAdapter(doctors);
        rcView = findViewById(R.id.recyclerView);
        rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcView.setAdapter(adapter);
    }
}