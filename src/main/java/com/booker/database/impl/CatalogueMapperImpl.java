package com.booker.database.impl;

import com.booker.database.CatalogueMapper;
import com.booker.database.QueryExecutor;
import com.booker.domain.Catalogue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueMapperImpl implements CatalogueMapper {
    private QueryExecutor executor;
    public CatalogueMapperImpl(){
        executor = QueryExecutor.getInstance();
    }

    public List<Catalogue> findCataloguesByHotelId(int id) {
        try {
            String sql = "select id, name, description, price " +
                    "from catalogues where hotelId = ?";
            ResultSet rs = executor.getResultSet(sql, id);
            List<Catalogue> hotels = new ArrayList<Catalogue>();
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
        Catalogue catalogue = new Catalogue();
        try {
            catalogue.setId(rs.getInt("id"));
            catalogue.setName(rs.getString("name"));
            catalogue.setDescription(rs.getString("description"));
            catalogue.setPrice(rs.getFloat("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogue;
    }
}
