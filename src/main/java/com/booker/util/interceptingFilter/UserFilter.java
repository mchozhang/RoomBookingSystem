package com.booker.util.interceptingFilter;

import com.booker.database.impl.UserMapperImpl;
import com.booker.domain.Booking;
import com.booker.domain.User;

public class UserFilter implements BookingFilter {
    @Override
    public String execute(Booking booking) {
        User customer = User.getUserById(booking.getUserId());
        if (customer == null) {
            return "customer doesn't exist";
        }
        return null;
    }
}
