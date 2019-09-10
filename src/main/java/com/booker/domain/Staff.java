package com.booker.domain;

public class Staff extends User {
    private Hotel hotel;

    public Staff() {
        super();
        this.role = "staff";
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
