package com.cov.covalsystraining;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskFormDAO {

    /*public void addNewTaskForm(TaskForm taskForm);

    public void updateTaskForm(TaskForm taskForm);

   public void deleteTaskForm(TaskForm taskForm);*/

    @Insert
    public void insertTaskForm(TaskForm taskForm);

    @Query("select * from taskform")
    LiveData<List<TaskForm>> getAllTaskFormDetails();

    @Query("select * from taskform where s_no==:s_no")
    public TaskForm getForm(int s_no);

}
