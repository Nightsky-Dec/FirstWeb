package com.example.demo.model.response;

import java.util.List;

public class ListResponse {
    private List<?> list;
    private Integer sum;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
