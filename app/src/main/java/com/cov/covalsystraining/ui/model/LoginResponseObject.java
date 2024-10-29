package com.cov.covalsystraining.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseObject {

    @SerializedName("DocEntry")
    @Expose
    private Integer docEntry;
    @SerializedName("EmpId")
    @Expose
    private String empId;
    @SerializedName("EmpName")
    @Expose
    private String empName;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("UserType")
    @Expose
    private String userType;
    @SerializedName("Department")
    @Expose
    private String department;
    @SerializedName("ReportedBy")
    @Expose
    private String reportedBy;

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

}
