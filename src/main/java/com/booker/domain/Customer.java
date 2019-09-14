package com.booker.domain;

public class Customer extends User {
    private String fullName;

    public Customer() {
        super();
        this.role = "customer";
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
