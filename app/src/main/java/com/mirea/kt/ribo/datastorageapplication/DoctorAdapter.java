package com.mirea.kt.ribo.datastorageapplication;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private ArrayList<Doctor> doctors;
    private OnItemSelectedListener listener;

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener {

        private final TextView nameView, specView, studyView;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            nameView = view.findViewById(R.id.tvDoctorName);
            specView = view.findViewById(R.id.tvDoctorSpec);
            studyView = view.findViewById(R.id.tvDoctorIsStudy);
        }

        @Override
        public void onClick(View v) {
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }
    }

    public DoctorAdapter(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public interface OnItemSelectedListener {

        void onSelected(Doctor doctor);

        void onMenuAction(Doctor doctor, MenuItem item);
    }

    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DoctorAdapter.ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.nameView.setText(doctor.getName());
        holder.specView.setText(doctor.getSpec());
        if(doctor.isStudy()){
            holder.studyView.setText("Прошел аттестацию");
        }else{
            holder.studyView.setText("Не прошел аттестацию");
        }
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }
    public void setDoctors(ArrayList<Doctor> doctors){
        this.doctors = doctors;
    }
}




