package com.booker.database.impl;

import com.booker.database.DataMapper;
import com.booker.database.QueryExecutor;
import com.booker.domain.BookerObj;
import com.booker.domain.Catalogue;
import com.booker.domain.Hotel;
import com.booker.domain.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelMapperImpl implements DataMapper {
    private QueryExecutor executor;

    public HotelMapperImpl() {
        executor = QueryExecutor.getInstance();
    }

    public ResultSet selectRowById(int id) {
        try {
            String sql = "select * from hotels where id = ?";
            return executor.getResultSet(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                return createEntity(rs);
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
                return createEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Hotel createEntity(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Location location = new Location(rs.getString("suburb"), rs.getString("address"));
            int version = rs.getInt("version");
            return new Hotel(id, name, location, version);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(BookerObj obj) {
        Hotel hotel = (Hotel) obj;
        String sql = "insert into hotels (name, suburb, address) values (?,?,?)";
        return executor.executeStatement(sql, hotel.getName(), hotel.getSuburb(), hotel.getAddress());
    }

    public int update(BookerObj obj) {
        Hotel hotel = (Hotel) obj;
        String sql = "update hotels set version = ?, name = ?, suburb = ?, address = ? where id = ?";
        return executor.executeStatement(sql, hotel.getVersion(), hotel.getName(), hotel.getSuburb(), hotel.getAddress(), hotel.getId());
    }

    public void delete(BookerObj obj) {
        Hotel hotel = (Hotel) obj;
        String sql = "delete from hotels where id = ?";
        executor.executeStatement(sql, hotel.getId());
    }
}
