package com.cov.covalsystraining.ui.gallery;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cov.covalsystraining.ApiClient;
import com.cov.covalsystraining.ApiInterface;
import com.cov.covalsystraining.R;
import com.cov.covalsystraining.TaskForm;
import com.cov.covalsystraining.TaskFormDatabase;
import com.cov.covalsystraining.databinding.FragmentActivityBinding;
import com.cov.covalsystraining.ui.model.ActivityModel;
import com.cov.covalsystraining.ui.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ActivityFragment extends Fragment {

    private FragmentActivityBinding binding;
    private EditText editTaskName,editTimeDuration,editRemarks,editStartDate,editEndDate;
    private Button savebutton;
    private Spinner editStatus;

    LiveData<List<TaskForm>> taskFormList;
    SharedPreferences sp ;
    String preferences = "mypref";

    TaskFormDatabase formDatabase;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ActivityViewModel galleryViewModel =
                new ViewModelProvider(this).get(ActivityViewModel.class);

        View view = inflater.inflate(R.layout.fragment_activity,container,false);

         editStartDate = view.findViewById(R.id.editTextStartDate);
         editEndDate = view.findViewById(R.id.editTextEndDate);
         editTaskName= view.findViewById(R.id.editTextTaskName);
         editTimeDuration = view.findViewById(R.id.editTextTimeDuration);
         editRemarks = view.findViewById(R.id.editTextRemarks);
         editStatus = view.findViewById(R.id.editTextStatus);

         int selectedItemofMySpinner = editStatus.getSelectedItemPosition();
         String actualPositionOfMySpinner = (String) editStatus.getItemAtPosition(selectedItemofMySpinner);
         sp = getActivity().getSharedPreferences(preferences, Context.MODE_PRIVATE);

         savebutton = view.findViewById(R.id.buttonSubmit);

        RoomDatabase.Callback mycallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };



        formDatabase = Room.databaseBuilder(getActivity(), TaskFormDatabase.class,
                "TaskForm").addCallback(mycallback).build();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String currentdate = sdf.format(new Date());



        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskname = editTaskName.getText().toString();
                String timeduration = editTimeDuration.getText().toString();
                String startdate = editStartDate.getText().toString();
                String enddate = editEndDate.getText().toString();
                String remarks = editRemarks.getText().toString();
                String status = editStatus.getSelectedItem().toString();
                String empid = sp.getString("empId","");
                String createdate = sdf.format(new Date());

                if (TextUtils.isEmpty(taskname)){
                    editTaskName.setError("TaskName is Required");
                    return;
                }
                if (TextUtils.isEmpty(timeduration)){
                    editTimeDuration.setError("TimeDuration is Required");
                    return;
                }
                if (TextUtils.isEmpty(startdate)){
                    editStartDate.setError("StartDate is Required");
                    return;
                }
                if (TextUtils.isEmpty(enddate)){
                    editEndDate.setError("EndDate is Required");
                    return;
                }
                if (TextUtils.isEmpty(remarks)){
                    editRemarks.setError("Remarks is Required");
                    return;
                }
                if (actualPositionOfMySpinner.isEmpty()){
                    setSpinnerError(editStatus,getString(Integer.parseInt("field can't be empty")));
                }

                // Room Database
                TaskForm taskForm = new TaskForm(taskname,timeduration,startdate,enddate,remarks,status);
                addTaskFormDetailsInBackground(taskForm);

                // API Function
                checktaskdetails(taskname,timeduration, convertDate(startdate), convertDate(enddate),remarks,status,empid,createdate);

                Log.e("Tag2",taskname);
                Log.e("Tag2",timeduration);
                Log.e("Tag2",startdate);
                Log.e("Tag2",enddate);
                Log.e("Tag2",remarks);
                Log.e("tag2",status);
                Log.e("tag2",empid);
                Log.e("tag2",createdate);

            }
        });

        editStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog();
            }
        });

        editEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDatePickerDialog();
            }
        });

        return view;
    }

    public String convertDate(String oldDate){
        SimpleDateFormat old = new SimpleDateFormat("dd/mm/yyyy");
        SimpleDateFormat New_ = new SimpleDateFormat("yyyy/mm/dd");
        try {
            Date old_ = old.parse(oldDate);
            return New_.format(old_);
        } catch (ParseException e) {
            Log.e("TAG", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void setSpinnerError(Spinner spinner, String error){
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error");
            selectedTextView.setTextColor(Color.RED);
            selectedTextView.setText(error);
            spinner.performClick();

        }
    }
    private void checktaskdetails(String taskname, String timeduration, String startdate, String enddate, String remarks, String status, String empid, String createdate){
        ActivityModel activityModel = new ActivityModel(taskname,timeduration,startdate,enddate,remarks,status,empid,createdate);
        Retrofit retrofit = ApiClient.getClient(getActivity());
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<Task> call = api.taskmodel(activityModel);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.body().getStatusCode() == 0){
                    Toast.makeText(getActivity(),response.body().getStatusMessage(),Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getActivity(),response.body().getStatusMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.e("TAG",t.toString());
                t.printStackTrace();
            }

        });
    }
        

    private void openDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);



        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                       String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        //  String selectedDate = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
                        editStartDate.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        // Show the date picker dialog
        datePickerDialog.show();

    }

    private void closeDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        editEndDate.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        // Show the date picker dialog
        datePickerDialog.show();
    }

    // Room DataBase function

    public void addTaskFormDetailsInBackground(TaskForm taskForm){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                formDatabase.getTaskFormDAO().insertTaskForm(taskForm);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      //  Toast.makeText(getActivity(),"Data saved successful",Toast.LENGTH_SHORT).show();
                        editTaskName.getText().clear();
                        editTimeDuration.getText().clear();
                        editStartDate.getText().clear();
                        editEndDate.getText().clear();
                        editRemarks.getText().clear();
                        editStatus.setSelection(0);
                    }
                });

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
//        Toast.makeText(getActivity(),"!",Toast.LENGTH_SHORT).show();
    }


}