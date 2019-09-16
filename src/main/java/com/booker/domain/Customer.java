package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.UnitOfWork;
import com.booker.database.impl.UserMapperImpl;

import java.sql.ResultSet;

public class Customer extends User {
    private String fullName;

    public Customer(String fullName) {
        super();
        this.role = "customer";
        this.fullName = fullName;
    }

    public Customer(int id, String fullName) {
        super();
        this.id = id;
        this.role = "customer";
        this.fullName = fullName;
        IdentityMap.putUser(id, this);
    }

    public String getFullName() {
        if (fullName == null) {
            load();
        }
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void load() {
        try {
            UserMapperImpl userMapper = new UserMapperImpl();
            ResultSet resultSet = userMapper.selectRowById(id);
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            role = resultSet.getString("role");
            fullName = resultSet.getString("full_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
