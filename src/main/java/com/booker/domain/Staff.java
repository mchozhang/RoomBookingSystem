package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.UnitOfWork;
import com.booker.database.impl.UserMapperImpl;

import java.sql.ResultSet;

public class Staff extends User {
    private int hotelId;

    public Staff(int hotelId) {
        super();
        this.role = "staff";
        this.hotelId = hotelId;
    }

    public Staff(int id, int hotelId, int version) {
        super();
        this.id = id;
        this.role = "staff";
        this.hotelId = hotelId;
        IdentityMap.putUser(id, this);
    }

    public int getHotelId() {
        if (hotelId < 1) {
            load();
        }
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return Hotel.getHotelById(hotelId).getName();
    }

    private void load() {
        try {
            UserMapperImpl userMapper = new UserMapperImpl();
            ResultSet resultSet = userMapper.selectRowById(id);
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            role = resultSet.getString("role");
            hotelId = resultSet.getInt("hotelId");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
