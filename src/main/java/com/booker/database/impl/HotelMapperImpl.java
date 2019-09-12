package com.booker.database.impl;

import com.booker.database.HotelMapper;
import com.booker.database.QueryExecutor;
import com.booker.domain.Hotel;
import com.booker.domain.Location;

import javax.management.relation.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelMapperImpl implements HotelMapper {
    private QueryExecutor executor;

    public HotelMapperImpl(){
        executor = QueryExecutor.getInstance();
    }

    public List<Hotel> findAllHotels() {
        try {
            String sql = "select * from hotels";
            ResultSet rs = executor.getResultSet(sql);
            List<Hotel> hotels = new ArrayList<Hotel>();
            while (rs.next()) {
                hotels.add(createEntity(rs));
            }
            return hotels;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Hotel findHotelById(int id) {
        try {
            String sql = "select * from hotels where id = ?";
            ResultSet rs = executor.getResultSet(sql, id);
            if (rs.next()) {
                Hotel hotel = createEntity(rs);
                return hotel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Hotel findHotelByName(String name) {
        try {
            String sql = "select * from hotels where name = ?";
            ResultSet rs = executor.getResultSet(sql, name);
            if (rs.next()) {
                Hotel hotel = createEntity(rs);
                return hotel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Hotel createEntity(ResultSet rs) {
        Hotel hotel = new Hotel();
        try {
            hotel.setId(rs.getInt("id"));
            hotel.setName(rs.getString("name"));
            Location location = new Location(rs.getString("suburb"), rs.getString("address"));
            hotel.setLocation(location);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

}
