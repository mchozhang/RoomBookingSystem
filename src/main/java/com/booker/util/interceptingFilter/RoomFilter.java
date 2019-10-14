package com.booker.util.interceptingFilter;

import com.booker.database.impl.RoomMapperImpl;
import com.booker.domain.Booking;
import com.booker.domain.Room;

public class RoomFilter implements BookingFilter {
    @Override
    public String execute(Booking booking) {
        RoomMapperImpl mapper = new RoomMapperImpl();
        Room room = mapper.findRoomById(booking.getRoomId());
        if (room == null) {
            return "the room doesn't exist";
        }
        return null;
    }
}
