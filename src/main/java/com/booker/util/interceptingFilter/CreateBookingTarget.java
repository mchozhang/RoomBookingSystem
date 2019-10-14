package com.booker.util.interceptingFilter;

import com.booker.domain.Booking;
import com.booker.domain.Room;

public class CreateBookingTarget {
    public String execute(Booking booking) {
        Room room = Room.getRoomById(booking.getRoomId());
        boolean result = Booking.createBooking(booking.getUserId(), room.getCatalogueId(), booking.getRoomId(), booking.getStartDate().toString(), booking.getEndDate().toString());
        if (result) {
            return "success";
        } else {
            return "failed to create booking";
        }
    }
}
