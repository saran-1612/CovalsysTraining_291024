package com.cov.covalsystraining.ui.employee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cov.covalsystraining.ApiClient;
import com.cov.covalsystraining.ApiInterface;
import com.cov.covalsystraining.R;
import com.cov.covalsystraining.ui.list.ListActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EmployeeFragment extends Fragment {

    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private List<ResponseObject> employeeList;
    private List<ResponseObject> filteredNameList;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EmployeeViewModel galleryViewModel =
                new ViewModelProvider(this).get(EmployeeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        searchView = view.findViewById(R.id.search_View);
        SharedPreferences sp = getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);
        String empid = sp.getString("getEmpId", "");
        recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        employeeList = new ArrayList<>();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
              //  Toast.makeText(getActivity(), "Clicked :" + adapter, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        adapter = new EmployeeAdapter(employeeList, new EmployeeAdapter.OnClickListener() {
            @Override
            public void onItemClick(ResponseObject employee) {
                Intent i = new Intent(getActivity(), ListActivity.class);
                i.putExtra("getEmpId", employee.getEmpId());
                i.putExtra("getEmpName", employee.getEmpName());
                i.putExtra("getDepartment", employee.getDepartment());
                startActivity(i);
                // Toast.makeText(getActivity(),"clicked:"+employee.getEmpName()+" and "+employee.getEmpId(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        getempDetails(empid);
        return view;
    }

    private void getempDetails(String empid) {
        Retrofit retrofit = ApiClient.getClient(getActivity());
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<EmployeeModel> call = api.getEmployee(empid);
        call.enqueue(new Callback<EmployeeModel>() {
            @Override
            public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                if (response.body().getStatusCode() == 0) {
                    List<ResponseObject> employeeModels = response.body().getResponseObject();
                    adapter.setList(employeeModels);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), response.body().getStatusMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EmployeeModel> call, Throwable t) {
                Log.e("TAG", t.toString());
                t.printStackTrace();
            }
        });
    }

}
