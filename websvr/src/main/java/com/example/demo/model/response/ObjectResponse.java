package com.example.demo.model.response;

public class ObjectResponse<T> extends Response {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
