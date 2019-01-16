package com.example.demo.model;

public class Response {
    private Integer status = 1;
    private String massage = "success";

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
