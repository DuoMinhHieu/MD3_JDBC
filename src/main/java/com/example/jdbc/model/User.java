package com.example.jdbc.model;

public class User {
    protected int price;
    protected int code;
    protected String name;
    protected String branch;
    protected String description;

    public User() {}

    public User(int price, int code, String name, String branch, String description) {
        this.price = price;
        this.code = code;
        this.name = name;
        this.branch = branch;
        this.description = description;
    }

    public User(String name, String email, String country) {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String description() {
        return null;
    }

    public String branch() {
        return null;
    }

    @Override
    public String toString() {
        return "user{" +
                "price=" + price +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
