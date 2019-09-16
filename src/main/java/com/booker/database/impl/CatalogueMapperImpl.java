package com.booker.database.impl;

import com.booker.database.DataMapper;
import com.booker.database.QueryExecutor;
import com.booker.domain.Catalogue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueMapperImpl implements DataMapper {
    private QueryExecutor executor;

    public CatalogueMapperImpl() {
        executor = QueryExecutor.getInstance();
    }

    public ResultSet selectRowById(int id) {
        try {
            String sql = "select * from catalogues where id = ?";
            return executor.getResultSet(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Catalogue> findCataloguesByHotelId(int hotelId) {
        try {
            String sql = "select * from catalogues where hotelId = ?";
            ResultSet rs = executor.getResultSet(sql, hotelId);
            List<Catalogue> hotels = new ArrayList<>();
            while (rs.next()) {
                hotels.add(createEntity(rs));
            }
            return hotels;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Catalogue createEntity(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String des = rs.getString("description");
            Float price = rs.getFloat("price");
            int hotelId = rs.getInt("hotelId");
            return new Catalogue(id, name, hotelId, des, price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(Object obj) {
        Catalogue catalogue = (Catalogue) obj;
        String sql = "insert into catalogues (name, hotelId, description, price) values (?,?,?,?)";
        return executor.executeStatement(sql, catalogue.getName(), catalogue.getHotelId(), catalogue.getDescription(), catalogue.getPrice());
    }

    public int update(Object obj) {
        Catalogue catalogue = (Catalogue) obj;
        String sql = "update catalogues set name = ?, hotelId = ?, description = ?, price = ? where id = ?";
        return executor.executeStatement(sql, catalogue.getName(), catalogue.getHotelId(), catalogue.getDescription(), catalogue.getPrice(), catalogue.getId());
    }

    public void delete(Object obj) {
        Catalogue catalogue = (Catalogue) obj;
        String sql = "delete from rooms where catalogueId = ?";
        executor.executeStatement(sql, catalogue.getId());
        sql = "delete from catalogues where id = ?";
        executor.executeStatement(sql, catalogue.getId());
    }
}
