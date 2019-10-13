package com.booker.database;

import java.sql.ResultSet;

/**
 * implicit optimistic lock
 */
public class LockingMapper implements DataMapper{
    private DataMapper mapper;

    public LockingMapper(DataMapper mapper) {
        this.mapper = mapper;

    }

    @Override
    public int update(Object object) {
        return 0;
    }

    @Override
    public ResultSet selectRowById(int id) {
        return null;
    }

    @Override
    public int insert(Object object) {
        return 0;
    }


    @Override
    public void delete(Object object) {

    }
}
