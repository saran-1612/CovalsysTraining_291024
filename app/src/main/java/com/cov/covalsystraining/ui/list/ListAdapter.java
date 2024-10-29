package com.cov.covalsystraining.ui.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cov.covalsystraining.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<ListResponseObject> employees;

    public ListAdapter(List<ListResponseObject> employees){
        this.employees = employees;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_employeelist,parent,false);
        return new ListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position){
        ListResponseObject employee = employees.get(position);
        holder.TextTaskName.setText(employee.getTaskName());
        Log.e("TAG2",employee.getTaskName());
        holder.TextTimeDuration.setText(employee.getTimeDuration());
        holder.TextStartDate.setText(employee.getStartDate());
        holder.TextEndDate.setText(employee.getEndDate());
        holder.TextRemarks.setText(employee.getRemarks());
        holder.TextStatus.setText(employee.getStatus());
        holder.TextCreatedDate.setText(employee.getCreatedDate());
    }

    @Override
    public int getItemCount(){
        return employees.size();
    }

    public void setList(List<ListResponseObject> employeeModels) {
        this.employees = employeeModels;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        TextView TextTaskName,TextTimeDuration,TextStartDate,TextEndDate,TextRemarks,TextStatus,TextCreatedDate;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            TextTaskName = itemView.findViewById(R.id.text_TaskName);
            TextTimeDuration = itemView.findViewById(R.id.text_TimeDuration);
            TextStartDate = itemView.findViewById(R.id.text_StartDate);
            TextEndDate = itemView.findViewById(R.id.text_EndDate);
            TextRemarks = itemView.findViewById(R.id.text_Remarks);
            TextStatus = itemView.findViewById(R.id.text_Status);
            TextCreatedDate = itemView.findViewById(R.id.text_CreatedDate);
        }
    }
}
