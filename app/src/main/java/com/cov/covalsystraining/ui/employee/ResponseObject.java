package com.cov.covalsystraining.ui.employee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseObject {

    @SerializedName("EmpName")
    @Expose
    private String empName;
    @SerializedName("EmpId")
    @Expose
    private String empId;
    @SerializedName("Department")
    @Expose
    private String department;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
