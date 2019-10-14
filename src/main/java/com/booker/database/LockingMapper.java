package com.booker.database;

import com.booker.domain.BookerObj;

import java.sql.ResultSet;

/**
 * implicit optimistic lock
 */
public class LockingMapper implements DataMapper {
    private DataMapper mapper;

    public LockingMapper(DataMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int update(BookerObj object) {
        try {
            ResultSet rs = mapper.selectRowById(object.getId());
            int version = 0;
            if (rs.next()) {
                version = rs.getInt("version");
            }
            if (object.getVersion() != version) {
                return 0;
            } else {
                object.setVersion(version + 1);
                UnitOfWork.getInstance().registerDirty(object);
                UnitOfWork.getInstance().commit();
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    @Override
    public void delete(BookerObj object) {
        try {
            ResultSet rs = mapper.selectRowById(object.getId());
            int version = 0;
            if (rs.next()) {
                version = rs.getInt("version");
            }
            if (object.getVersion() != version) {
                return;
            } else {
                UnitOfWork.getInstance().registerDelete(object);
                UnitOfWork.getInstance().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet selectRowById(int id) {
        return null;
    }

    @Override
    public int insert(BookerObj object) {
        return 0;
    }
}
