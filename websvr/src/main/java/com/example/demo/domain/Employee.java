package com.example.demo.domain;

public class Employee {
    String name;
    int age;
    String designation;
    double salary;

    public String getName() { return name; }

    public void setName(String name){ this.name = name; }

    public int getAge() {
        return age;
    }

    public void setAge(int age) { this.age = age; }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // 打印信息
    public void printEmployee(){
        System.out.println("名字:" + name);
        System.out.println("年龄:" + age);
        System.out.println("职位:" + designation);
        System.out.println("薪水:" + salary);
    }
}
