package com.cov.covalsystraining.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginModel {

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;
    @SerializedName("responseObject")

    @Expose
    private List<LoginResponseObject> responseObject;

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

    public List<LoginResponseObject> getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(List<LoginResponseObject> responseObject) {
        this.responseObject = responseObject;
    }

}
