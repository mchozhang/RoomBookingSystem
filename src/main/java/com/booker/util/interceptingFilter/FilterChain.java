package com.booker.util.interceptingFilter;

import com.booker.domain.Booking;
import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<BookingFilter> filters = new ArrayList<BookingFilter>();
    private CreateBookingTarget target;

    public void addFilter(BookingFilter filter) {
        filters.add(filter);
    }

    public String execute(Booking booking) {
        List<String> logs = new ArrayList<>();
        for (BookingFilter filter : filters) {
            String log = filter.execute(booking);
            if (log != null) {
                logs.add(log);
            }
        }
        logs.add(target.execute(booking));
        return String.join(",", logs);
    }

    public void setTarget(CreateBookingTarget target) {
        this.target = target;
    }
}