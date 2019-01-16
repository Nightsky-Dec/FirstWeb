package com.example.demo.domain;

public class People {
    private Integer id;
    private String name;
    private Integer age = 10;

    public People() {
        System.out.println("初始化：" + age);
    }

    public int NewAge(int age) {
        return age;
    }

    public static void main(String[] args) {
        People AD = new People();
        System.out.println("新age：" + AD.NewAge(22));
        System.out.println("this："+ AD.getAge());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
