package com.cov.covalsystraining.ui.employee;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cov.covalsystraining.R;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private List<ResponseObject> employees;
    private OnClickListener onClickListener;
    private List<ResponseObject> filteredNameList;

    public EmployeeAdapter(List<ResponseObject> employees, OnClickListener onClickListener){
        this.employees = employees;
        this.filteredNameList = employees ;
        this.onClickListener=onClickListener;
    }
    public Filter getFilter(){
        return  new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if (charSequenceString.isEmpty()){
                    filteredNameList = employees;
                }else {
                    List<ResponseObject> filteredList = new ArrayList<>();
                    for (ResponseObject item : employees){
                        if (item.getEmpName().toLowerCase().contains(charSequenceString.toLowerCase())){
                            filteredList.add(item);
                            Log.e("TAG12",item.toString());
                        }
                        filteredNameList = filteredList;
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredNameList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
              //  filteredNameList.clear();
                filteredNameList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_employeelist,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position){
        ResponseObject employee = employees.get(position);
        holder.nameTextView.setText(employee.getEmpName());
        holder.idTextView.setText(employee.getEmpId());
        holder.deptTextView.setText(employee.getDepartment());
        holder.bind(employee ,onClickListener);
    }

    @Override
    public int getItemCount(){
        return employees.size();
    }
    public interface OnClickListener{
        void onItemClick(ResponseObject employee);
    }

    public void setList(List<ResponseObject> employeeModels) {
        this.employees = employeeModels;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView nameTextView,idTextView,deptTextView;
        public ViewHolder(@NonNull View itemView){
            super(itemView.getRootView());
            nameTextView = itemView.findViewById(R.id.text_name);
            idTextView = itemView.findViewById(R.id.text_id);
            deptTextView = itemView.findViewById(R.id.text_dept);
        }

        void bind (final ResponseObject employee, final OnClickListener onClickListener){
            nameTextView.setText(employee.getEmpName());
            idTextView.setText(employee.getEmpId());
            deptTextView.setText(employee.getDepartment());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClick(employee);
                }
            });
        }
    }

}

