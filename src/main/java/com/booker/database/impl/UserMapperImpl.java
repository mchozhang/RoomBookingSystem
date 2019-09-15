package com.booker.database.impl;

import com.booker.database.HotelMapper;
import com.booker.database.QueryExecutor;
import com.booker.database.UserMapper;
import com.booker.domain.Customer;
import com.booker.domain.Staff;
import com.booker.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperImpl implements UserMapper {
    private QueryExecutor executor;

    public UserMapperImpl() {
        executor = QueryExecutor.getInstance();
    }

    public User findUserById(int id) {
        try {
            String sql = "select * from users where id = ?";

            ResultSet rs = executor.getResultSet(sql, id);
            if (rs.next()) {
                User user = createEntity(rs);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUserByUsernamePassword(String username, String password) {
        try {
            String sql = "select * from users where username = ? and password = ?";

            ResultSet rs = executor.getResultSet(sql, username, password);
            if (rs.next()) {
                User user = createEntity(rs);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User createEntity(ResultSet rs) {
        try {
            String role = rs.getString("role");
            if (role.equals("customer")){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFullName(rs.getString("full_name"));
                return customer;
            } else if (role.equals("staff")) {
                Staff staff = new Staff();
                staff.setId((rs.getInt("id")));
                int hotelId = rs.getInt("hotelId");
                HotelMapper mapper = new HotelMapperImpl();
                staff.setHotel(mapper.findHotelById(hotelId));
                return staff;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
