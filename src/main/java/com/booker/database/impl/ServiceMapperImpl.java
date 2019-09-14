package com.booker.database.impl;

import com.booker.database.QueryExecutor;
import com.booker.database.ServiceMapper;
<<<<<<< HEAD
=======
import com.booker.domain.Hotel;
import com.booker.domain.Location;
>>>>>>> ef5fa25ec4ac063113172a2f242b810f74ff20f3
import com.booker.domain.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceMapperImpl implements ServiceMapper {
    private QueryExecutor executor;

    public ServiceMapperImpl(){
        executor = QueryExecutor.getInstance();
    }

    public List<Service> findServicesByHotelId(int id) {
        try {
<<<<<<< HEAD
            String sql = "select services.id, services.name " +
                    "from hotels_services join services on hotels_services.serviceId = services.id " +
                    "where hotels_services.hotelId = ?";
=======
            String sql = "select services.id, services.name from hotels_services join services on hotels_services.serviceId = services.id where hotels_services.hotelId = ?";
>>>>>>> ef5fa25ec4ac063113172a2f242b810f74ff20f3
            ResultSet rs = executor.getResultSet(sql, id);
            List<Service> services = new ArrayList<Service>();
            while (rs.next()) {
                services.add(createEntity(rs));
            }
            return services;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Service createEntity(ResultSet rs) {
        Service service = new Service();
        try {
            service.setId(rs.getInt("id"));
            service.setName(rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }
}
