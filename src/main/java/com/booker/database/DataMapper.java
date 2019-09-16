package com.booker.database;

import java.sql.ResultSet;

public interface DataMapper {
    int insert(Object object);
    int update(Object object);
    void delete(Object object);
    ResultSet selectRowById(int id);
}
