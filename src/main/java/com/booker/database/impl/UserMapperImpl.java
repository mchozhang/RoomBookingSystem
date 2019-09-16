package com.booker.database.impl;

import com.booker.database.DataMapper;
import com.booker.database.QueryExecutor;
import com.booker.domain.Customer;
import com.booker.domain.Staff;
import com.booker.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperImpl implements DataMapper {
    private QueryExecutor executor;

    public UserMapperImpl() {
        executor = QueryExecutor.getInstance();
    }

    public ResultSet selectRowById(int id) {
        try {
            String sql = "select * from users where id = ?";
            return executor.getResultSet(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                return createEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User createEntity(ResultSet rs) {
        try {
            String role = rs.getString("role");
            int id = rs.getInt("id");
            if (role.equals("customer")){
                String fullName = rs.getString("full_name");
                Customer customer = new Customer(id, fullName);
                return customer;
            } else if (role.equals("staff")) {
                int hotelId = rs.getInt("hotelId");
                Staff staff = new Staff(id, hotelId);
                return staff;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(Object user){
        return 0;
    }

    public int update(Object user){
        return 0;
    }

    public void delete(Object user){

    }
}
