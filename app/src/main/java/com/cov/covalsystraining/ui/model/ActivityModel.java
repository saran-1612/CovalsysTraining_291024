package com.cov.covalsystraining.ui.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityModel {
        @SerializedName("TaskName")
        @Expose
        private String taskName;
        @SerializedName("TimeDuration")
        @Expose
        private String timeDuration;
        @SerializedName("StartDate")
        @Expose
        private String startDate;
        @SerializedName("EndDate")
        @Expose
        private String endDate;
        @SerializedName("Remarks")
        @Expose
        private String remarks;
        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("EmpId")
        @Expose
        private String empId;
        @SerializedName("CreatedDate")
        @Expose
        private String createdDate;

    public ActivityModel(String taskname, String timeduration, String startdate, String enddate, String remarks, String status, String empid, String createdate) {
        this.taskName= taskname;
        this.timeDuration = timeduration;
        this.startDate = startdate;
        this.endDate = enddate;
        this.remarks = remarks;
        this.status = status;
        this.empId =empid;
        this.createdDate =createdate;
    }

    public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTimeDuration() {
            return timeDuration;
        }

        public void setTimeDuration(String timeDuration) {
            this.timeDuration = timeDuration;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

    }

