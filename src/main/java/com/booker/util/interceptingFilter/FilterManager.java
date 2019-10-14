package com.booker.util.interceptingFilter;

import com.booker.domain.Booking;

public class FilterManager {
    private FilterChain filterChain;

    public FilterManager(CreateBookingTarget target){
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }
    public void setFilter(BookingFilter filter){
        filterChain.addFilter(filter);
    }

    public String filterBooking(Booking booking){
        return filterChain.execute(booking);
    }
}
