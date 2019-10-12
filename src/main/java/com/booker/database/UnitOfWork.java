package com.booker.database;

import com.booker.database.impl.*;
import com.booker.domain.*;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private UnitOfWork() {
        newObjects = new ArrayList<>();
        dirtyObjects = new ArrayList<>();
        deleteObjects = new ArrayList<>();
    }

    private static UnitOfWork instance = null;
    private List<Object> newObjects;
    private List<Object> dirtyObjects;
    private List<Object> deleteObjects;


    public static UnitOfWork getInstance() {
        if (instance == null) {
            instance = new UnitOfWork();
        }
        return instance;
    }

    public void registerNew(Object object) {
        if (!dirtyObjects.contains(object) && !dirtyObjects.contains(object)) {
            newObjects.add(object);
        }
    }

    public void registerDirty(Object object) {
        if (!dirtyObjects.contains(object) && !newObjects.contains(object)) {
            dirtyObjects.add(object);
        }
    }

    public void registerDelete(Object object) {
        if (newObjects.remove(object)){
            return;
        }
        dirtyObjects.remove(object);
        if (!deleteObjects.contains(object)) {
            deleteObjects.add(object);
        }
    }

    public void commit() {
        for (Object object : newObjects) {
            DataMapper mapper = getMapper(object);
            mapper.insert(object);
        }
        for (Object object : dirtyObjects) {
            DataMapper mapper = getMapper(object);
            mapper.update(object);
        }
        for (Object object : deleteObjects) {
            DataMapper mapper = getMapper(object);
            mapper.delete(object);
        }
        clear();
    }

    public void clear() {
        newObjects.clear();
        dirtyObjects.clear();
        deleteObjects.clear();
    }

    private DataMapper getMapper(Object object) {
        if (object instanceof User) {
            return new UserMapperImpl();
        }
        if (object instanceof Room) {
            return new RoomMapperImpl();
        }
        if (object instanceof Catalogue) {
            return new CatalogueMapperImpl();
        }
        if (object instanceof Hotel) {
            return new HotelMapperImpl();
        }
        if (object instanceof Service) {
            return new ServiceMapperImpl();
        }
        return null;
    }

}
