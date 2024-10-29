package com.cov.covalsystraining;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="TaskForm")
public class TaskForm {

    @ColumnInfo(name = "s_no")
    @PrimaryKey(autoGenerate = true)
    int id ;
    @ColumnInfo(name = "TaskName")
    String taskname;
    @ColumnInfo(name = "TimeDuration")
    String timeduration;
    @ColumnInfo(name = "StartDate")
    String startdate;
    @ColumnInfo(name = "EndDate")
    String enddate;
    @ColumnInfo(name = "Remarks")
    String remarks;
    @ColumnInfo(name = "Status")
    String status;



    public TaskForm(String taskname, String timeduration, String startdate, String enddate, String remarks, String status) {
        this.id=0;
        this.taskname = taskname;
        this.timeduration = timeduration;
        this.startdate = startdate;
        this.enddate = enddate;
        this.remarks = remarks;
        this.status = status;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTimeduration() {
        return timeduration;
    }

    public void setTimeduration(String timeduration) {
        this.timeduration = timeduration;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
