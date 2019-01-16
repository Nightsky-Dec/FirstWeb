package com.example.demo.bean;

public class IllegalStatus {
    private String type_id;
    private String name;
    private String enable;
    private String status;
    private String status_description;
    private String illegal_condition;
    private String count;
    private long create_time;
    private long last_modify_time;
    private long status_time;
    private int expired;


    public String getIllegal_condition() {
        return illegal_condition;
    }

    public void setIllegal_condition(String illegal_condition) {
        this.illegal_condition = illegal_condition;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_description() {
        return status_description;
    }

    public void setStatus_description(String status_description) {
        this.status_description = status_description;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(long last_modify_time) {
        this.last_modify_time = last_modify_time;
    }

    public long getStatus_time() {
        return status_time;
    }

    public void setStatus_time(long status_time) {
        this.status_time = status_time;
    }
}
