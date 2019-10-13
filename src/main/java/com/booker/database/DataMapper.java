package com.booker.database;

import com.booker.domain.BookerObj;

import java.sql.ResultSet;

public interface DataMapper {
    int insert(BookerObj object);
    int update(BookerObj object);
    void delete(BookerObj object);
    ResultSet selectRowById(int id);
}
