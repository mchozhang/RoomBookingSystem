package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.UnitOfWork;
import com.booker.database.impl.RoomMapperImpl;

import java.sql.ResultSet;

public class Room {
    private int id;
    private String number;
    private int catalogueId;

    public Room(String number, int catalogueId) {
        this.number = number;
        this.catalogueId = catalogueId;
        UnitOfWork.getInstance().registerNew(this);
    }

    public Room(int id, String number, int catalogueId) {
        this.id = id;
        this.number = number;
        this.catalogueId = catalogueId;
        IdentityMap.putRoom(id, this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        if (number == null) {
            load();
        }
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        UnitOfWork.getInstance().registerDirty(this);
    }

    public int getCatalogueId() {
        if (catalogueId < 1) {
            load();
        }
        return catalogueId;
    }

    public void setCatalogueId(int catalogueId) {
        this.catalogueId = catalogueId;
        UnitOfWork.getInstance().registerDirty(this);
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
