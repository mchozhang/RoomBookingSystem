package com.booker.util.interceptingFilter;

import com.booker.domain.Booking;

public interface BookingFilter {
    String execute(Booking booking);
}
