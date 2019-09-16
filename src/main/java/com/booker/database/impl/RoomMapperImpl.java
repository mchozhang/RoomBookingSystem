package com.booker.database.impl;

import com.booker.database.DataMapper;
import com.booker.database.QueryExecutor;
import com.booker.domain.Hotel;
import com.booker.domain.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomMapperImpl implements DataMapper {
    private QueryExecutor executor;

    public RoomMapperImpl() {
        executor = QueryExecutor.getInstance();
    }

    public ResultSet selectRowById(int id) {
        try {
            String sql = "select * from rooms where id = ?";
            return executor.getResultSet(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> findRoomsByCatalogueId(int catalogueId) {
        try {
            String sql = "select id, catalogueId, number from rooms " +
                    "where catalogueId = ?";
            ResultSet rs = executor.getResultSet(sql, catalogueId);
            List<Room> rooms = new ArrayList<>();
            while (rs.next()) {
                rooms.add(createEntity(rs));
            }
            return rooms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Room createEntity(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String number = rs.getString("number");
            int catalogueId = rs.getInt("catalogueId");
            return new Room(id, number, catalogueId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(Object obj) {
        Room room = (Room) obj;
        String sql = "insert into rooms (number, catalogueId) values (?,?)";
        return executor.executeStatement(sql, room.getNumber(), room.getCatalogueId());
    }

    public int update(Object obj) {
        Room room = (Room) obj;
        String sql = "update rooms set number = ?, catalogueId = ? where id = ?";
        return executor.executeStatement(sql, room.getNumber(), room.getCatalogueId(), room.getId());
    }

    public void delete(Object obj) {
        Room room = (Room) obj;
        String sql = "delete from rooms where id = ?";
        executor.executeStatement(sql, room.getId());
    }
}
