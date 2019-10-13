package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.UnitOfWork;
import com.booker.database.impl.ServiceMapperImpl;

import java.sql.ResultSet;

public class Service extends BookerObj {
    private String name;

    public Service(String name){
        this.name = name;
    }

    public Service(int id, String name, int version){
        this.id = id;
        this.name = name;
        IdentityMap.putService(id, this);
    }

    public String getName() {
        if (name == null) {
            load();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void load() {
        try {
            ServiceMapperImpl mapper = new ServiceMapperImpl();
            ResultSet resultSet = mapper.selectRowById(id);
            name = resultSet.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Service) {
            Service service = (Service) obj;
            return service.getName().equals(name);
        } else {
            return false;
        }
    }
}
