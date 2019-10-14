package com.booker.util.interceptingFilter;

import com.booker.domain.Booking;

public class DateRangeFilter implements BookingFilter {
    @Override
    public String execute(Booking booking) {
        if (booking.hasDateConflict()) {
            return "the date conflicts with others bookings";
        }
        return null;
    }
}
