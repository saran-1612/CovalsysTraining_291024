package com.cov.covalsystraining;

import com.cov.covalsystraining.ui.employee.EmployeeModel;
import com.cov.covalsystraining.ui.list.ListModel;
import com.cov.covalsystraining.ui.model.ActivityModel;
import com.cov.covalsystraining.ui.model.LoginModel;
import com.cov.covalsystraining.ui.model.Task;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/Login/validateLogin")
    Call<LoginModel> checkLogin(@Query("UserName") String userName, @Query("Password") String password);

    @POST("api/Login/addActivity")
    Call<Task> taskmodel(@Body ActivityModel task);

    @GET("api/Login/getEmpActivity")
    Call<EmployeeModel> getEmployee(@Query("EmpId") String empid);

    @GET("api/Login/getEmployeeActivity")
    Call<ListModel> getList(@Query("EmpId") String empid);
}
