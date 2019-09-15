package com.booker.database.impl;

import com.booker.database.QueryExecutor;
import com.booker.database.ServiceMapper;
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
            String sql = "select services.id, services.name " +
                    "from hotels_services join services on hotels_services.serviceId = services.id " +
                    "where hotels_services.hotelId = ?";
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
