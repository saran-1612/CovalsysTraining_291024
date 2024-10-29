package com.cov.covalsystraining.ui.list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListModel {
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("statusMessage")
    @Expose
    private String statusMessage;
    @SerializedName("responseObject")
    @Expose
    private List<ListResponseObject> listresponseObject;

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

    public List<ListResponseObject> getResponseObject() {
        return listresponseObject;
    }

    public void setResponseObject(List<ListResponseObject> responseObject) {
        this.listresponseObject = responseObject;
    }
}
