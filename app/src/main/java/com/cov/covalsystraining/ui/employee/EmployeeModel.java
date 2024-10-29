package com.cov.covalsystraining.ui.employee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeModel {

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;
    @SerializedName("responseObject")
    @Expose
    private List<ResponseObject> responseObject;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<ResponseObject> getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(List<ResponseObject> responseObject) {
        this.responseObject = responseObject;
    }

}
