package com.booker.database.impl;

import com.booker.database.DataMapper;
import com.booker.database.QueryExecutor;
import com.booker.domain.BookerObj;
import com.booker.domain.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceMapperImpl implements DataMapper {
    private QueryExecutor executor;

    public ServiceMapperImpl() {
        executor = QueryExecutor.getInstance();
    }

    public ResultSet selectRowById(int id) {
        try {
            String sql = "select * from services where id = ?";
            return executor.getResultSet(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Service> findServicesByHotelId(int id) {
        try {
            String sql = "select services.id, services.version, services.name " +
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

    public Service findServiceByName(String name) {
        try {
            String sql = "select * from services where name = ?";
            ResultSet rs = executor.getResultSet(sql, name);
            if (rs.next()) {
                return createEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addHotelServiceMap(int hotelId, String serviceName) {
        Service service = findServiceByName(serviceName);
        int serviceId = 0;
        if (service == null) {
            service = new Service(serviceName);
            serviceId = insert(service);
        } else {
            serviceId = service.getId();
        }
        insertHotelServiceMap(hotelId, serviceId);
    }

    public void removeHotelServiceMap(int hotelId, int serviceId) {
        String sql = "delete from hotels_services where hotelId = ? and serviceId = ?";
        executor.executeStatement(sql, hotelId, serviceId);
    }

    public void insertHotelServiceMap(int hotelId, int serviceId) {
        String sql = "insert into hotels_services (hotelId, serviceId) values (?,?)";
        executor.executeStatement(sql, hotelId, serviceId);
    }


    private Service createEntity(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            int version = rs.getInt("version");
            String name = rs.getString("name");
            return new Service(id, name, version);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(BookerObj obj) {
        Service service = (Service) obj;
        String sql = "insert into services (name, version) values (?,?)";
        return executor.executeStatement(sql, service.getName(), service.getVersion());
    }

    public int update(BookerObj obj) {
        Service service = (Service) obj;
        String sql = "update services set version = ?, name = ? where id = ?";
        return executor.executeStatement(sql, service.getVersion(), service.getName(), service.getId());
    }

    public void delete(BookerObj obj) {
        Service service = (Service) obj;
        String sql = "delete from services where id = ?";
        executor.executeStatement(sql, service.getId());
    }
}
