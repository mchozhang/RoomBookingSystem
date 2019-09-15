package com.booker.database.impl;

import com.booker.database.QueryExecutor;
import com.booker.database.RoomMapper;
import com.booker.domain.Room;
import com.booker.domain.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomMapperImpl implements RoomMapper {
    private QueryExecutor executor;

    public RoomMapperImpl() {
        executor = QueryExecutor.getInstance();
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
        Room room = new Room();
        try {
            room.setId(rs.getInt("id"));
            room.setNumber(rs.getString("number"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
}
