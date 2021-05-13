package com.example.orangefly.models;
import java.util.List;

public class DefaultResponse {
    private int status;
    private String message;
    private List<Login> params;

    public DefaultResponse(int status, String message, List<Login> params) {
        this.status = status;
        this.message = message;
        this.params = params;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Login> getParams() {
        return params;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setParams(List<Login> params) {
        this.params = params;
    }
}
