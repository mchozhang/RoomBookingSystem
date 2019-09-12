package com.booker.database;

import com.booker.domain.Service;

import java.util.List;

public interface ServiceMapper {
    List<Service> findServicesByHotelId(int id);
}
