package com.booker.domain;

public class Customer extends User {
    private String fullName;

    public Customer(String fullName) {
        super();
        this.role = "customer";
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
