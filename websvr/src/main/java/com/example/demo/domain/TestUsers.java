package com.example.demo.domain;

import java.util.ArrayList;

public class TestUsers {
    private ArrayList<TestUser> testUsers;

    public TestUsers() {}

    public TestUsers(ArrayList<TestUser> testUsers) {
        this.testUsers = testUsers;
    }

    public ArrayList<TestUser> getTestUsers() {
        return testUsers;
    }

    public void setTestUsers(ArrayList<TestUser> testUsers) {
        this.testUsers = testUsers;
    }
}
