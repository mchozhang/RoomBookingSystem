package com.booker.database;

import com.booker.domain.Hotel;

public interface HotelMapper {
    Hotel findHotelByName(String string);
    Hotel findHotelById(int id);
}
