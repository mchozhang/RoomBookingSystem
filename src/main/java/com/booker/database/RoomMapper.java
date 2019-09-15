package com.booker.database;

import com.booker.domain.Room;

import java.util.List;

public interface RoomMapper {
    List<Room> findRoomsByCatalogueId(int catalogue);
}
