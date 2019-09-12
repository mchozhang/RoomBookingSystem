package com.booker.database;

import com.booker.domain.Hotel;
import com.booker.domain.Service;

import java.util.List;

public interface HotelMapper {
    Hotel findHotelByName(String string);
    Hotel findHotelById(int id);
    List<Hotel> findAllHotels();
}
