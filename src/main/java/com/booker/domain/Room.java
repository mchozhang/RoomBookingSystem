package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.impl.BookingMapperImpl;
import com.booker.database.impl.RoomMapperImpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

public class Room extends BookerObj {
    private String number;
    private int catalogueId;

    public Room(String number, int catalogueId) {
        this.number = number;
        this.catalogueId = catalogueId;
    }

    public Room(int id, String number, int catalogueId, int version) {
        this.id = id;
        this.version = version;
        this.number = number;
        this.catalogueId = catalogueId;
        IdentityMap.putRoom(id, this);
    }

    public static Room getRoomById(int id) {
        Room room = IdentityMap.getRoom(id);
        if (room == null) {
            RoomMapperImpl mapper = new RoomMapperImpl();
            room = mapper.findRoomById(id);
        }
        return room;
    }

    public boolean isAvailable(String start, String end) {
        Date startDate = Date.valueOf(start);
        Date endDate = Date.valueOf(end);
        BookingMapperImpl mapper = new BookingMapperImpl();
        List<Booking> bookings = mapper.findBookingByRoomWithinDate( id, startDate, endDate);
        return bookings.size() == 0;
    }

    public String getNumber() {
        if (number == null) {
            load();
        }
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCatalogueId() {
        if (catalogueId < 1) {
            load();
        }
        return catalogueId;
    }

    public void setCatalogueId(int catalogueId) {
        this.catalogueId = catalogueId;
    }

    private void load() {
        try {
            RoomMapperImpl mapper = new RoomMapperImpl();
            ResultSet resultSet = mapper.selectRowById(id);
            number = resultSet.getString("number");
            catalogueId = resultSet.getInt("catalogueId");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Room) {
            Room room = (Room) obj;
            return room.getId() == id && room.getCatalogueId() == catalogueId && room.getNumber().equals(number);
        } else {
            return false;
        }
    }
}
