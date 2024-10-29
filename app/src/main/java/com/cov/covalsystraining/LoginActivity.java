package com.cov.covalsystraining;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cov.covalsystraining.ui.model.LoginModel;
import com.cov.covalsystraining.ui.model.LoginResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public  class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String mypreference = "mypref";

    protected void onCreate(Bundle savedInstanceState) {

        Button btn;
        EditText ed1,ed2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        btn = (Button)findViewById(R.id.login);
        ed1 =(EditText) findViewById(R.id.empid);
        ed2 = (EditText) findViewById(R.id.password);

        EditText empid = (EditText) findViewById(R.id.empid);
        EditText password = (EditText) findViewById(R.id.password);

        sharedPreferences = getApplicationContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("IsLogged",false)){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = empid.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(number)){
                    ed1.setError("EmpID is required");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    ed2.setError("Password is required");
                    return;
                }
                checkLoginDetails(number, pass);
            }
        });
    }

    private void checkLoginDetails(String userName, String password) {
        Retrofit retrofit = ApiClient.getClient(this);
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<LoginModel> call = api.checkLogin(userName, password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.body().getStatusCode() == 0){
                    List<LoginResponseObject> loginResponseObjectList = response.body().getResponseObject();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",loginResponseObjectList.get(0).getEmpName());
                    editor.putString("empId",loginResponseObjectList.get(0).getEmpId());
                    editor.putString("department",loginResponseObjectList.get(0).getDepartment());
                    editor.putString("reportedBy",loginResponseObjectList.get(0).getReportedBy());
                    editor.putString("userType",loginResponseObjectList.get(0).getUserType());
                    editor.putBoolean("IsLogged",true);
                    editor.commit();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),response.body().getStatusMessage(),Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(),response.body().getStatusMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e("TAG",t.toString());
                t.printStackTrace();
            }

        });
    }

}



