package com.cov.covalsystraining.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cov.covalsystraining.ApiClient;
import com.cov.covalsystraining.ApiInterface;
import com.cov.covalsystraining.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private List<ListResponseObject> activityList;

    @Nullable
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent i = getIntent();
        String EmpId =  i.getStringExtra("getEmpId");
        Log.e("Tag",EmpId);
        recyclerView = findViewById(R.id.act_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        activityList = new ArrayList<>();
        adapter = new ListAdapter(activityList);
        recyclerView.setAdapter(adapter);
        getlistDetails(EmpId);
    }
    private void getlistDetails(String empid){
        Retrofit retrofit = ApiClient.getClient(getApplicationContext());
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<ListModel> call = api.getList(empid);
        call.enqueue(new Callback<ListModel>() {
            @Override
            public void onResponse(Call<ListModel> call, Response<ListModel>response){
                if (response.body().getStatusCode()==0){
                     List<ListResponseObject> employeeModel = response.body().getResponseObject();
                     adapter.setList(employeeModel);
                     adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(),response.body().getStatusMessage(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ListModel> call, Throwable t) {
                Log.e("TAG",t.toString());
                t.printStackTrace();
            }
        });
    }

}
