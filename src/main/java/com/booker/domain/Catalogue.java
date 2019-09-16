package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.UnitOfWork;
import com.booker.database.impl.CatalogueMapperImpl;
import com.booker.database.impl.RoomMapperImpl;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class Catalogue {
    private int id;
    private String name;
    private int hotelId;
    private String description;
    private Float price;

    public Catalogue(int id, String name, int hotelId, String des, Float price) {
        this.id = id;
        this.name = name;
        this.hotelId = hotelId;
        this.description = des;
        this.price = price;
        IdentityMap.putCatalogue(id, this);
    }

    public Catalogue(String name, int hotelId, String des, Float price) {
        this.name = name;
        this.hotelId = hotelId;
        this.description = des;
        this.price = price;
    }

    public List<Room> getRooms() {
        RoomMapperImpl mapper = new RoomMapperImpl();
        return mapper.findRoomsByCatalogueId(this.id);
    }

    public String getRoomNumberStr() {
        List<Room> rooms = getRooms();
        String[] roomNumberList = new String[rooms.size()];
        for (int i = 0; i < rooms.size(); i++) {
            roomNumberList[i] = rooms.get(i).getNumber();
        }
        return String.join(",", roomNumberList);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        if (description == null) {
            load();
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Float getPrice() {
        if (price == null) {
            load();
        }
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getHotelId() {
        if (hotelId < 1) {
            load();
        }
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    private void load() {
        try {
            CatalogueMapperImpl mapper = new CatalogueMapperImpl();
            ResultSet resultSet = mapper.selectRowById(id);
            name = resultSet.getString("name");
            hotelId = resultSet.getInt("hotelId");
            description = resultSet.getString("description");
            price = resultSet.getFloat("price");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void addCatalogue(String name, String des, int hotelId, String priceStr, String[] rooms) {
        try {
            float price = Float.parseFloat(priceStr);
            Catalogue catalogue = new Catalogue(name, hotelId, des, price);
            CatalogueMapperImpl mapper = new CatalogueMapperImpl();
            int catalogueId = mapper.insert(catalogue);

            RoomMapperImpl roomMapper = new RoomMapperImpl();
            for (String number : rooms) {
                roomMapper.insert(new Room(number, catalogueId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void editCatalogue(String id, String name, String des, String priceStr, String[] roomNumbers) {
        try {
            float price = Float.parseFloat(priceStr);
            int catalogueId = Integer.parseInt(id);
            Catalogue catalogue = IdentityMap.getCatalogue(catalogueId);
            catalogue.setName(name);
            catalogue.setDescription(des);
            catalogue.setPrice(price);
            UnitOfWork.getInstance().registerDirty(catalogue);

            List<Room> originalRooms = catalogue.getRooms();
            List<String> originalRoomNumbers = Arrays.asList(catalogue.getRoomNumberStr().split(","));
            List<String> newRoomNumbers = Arrays.asList(roomNumbers);
            // remove rooms
            for (Room originalRoom : originalRooms) {
                if (!newRoomNumbers.contains(originalRoom.getNumber())) {
                    UnitOfWork.getInstance().registerDelete(originalRoom);
                }
            }

            // add new rooms
            for (String newRoomNumber: newRoomNumbers) {
                if (!originalRoomNumbers.contains(newRoomNumber)) {
                    Room room = new Room(newRoomNumber, catalogueId);
                    UnitOfWork.getInstance().registerNew(room);
                }
            }
            UnitOfWork.getInstance().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void deleteCatalogue(String id) {
        try {
            int catalogueId = Integer.parseInt(id);
            Catalogue catalogue = IdentityMap.getCatalogue(catalogueId);
            CatalogueMapperImpl mapper = new CatalogueMapperImpl();
            mapper.delete(catalogue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Catalogue) {
            Catalogue catalogue = (Catalogue) obj;
            return catalogue.getId() == id && catalogue.getName().equals(name) &&
                    catalogue.getDescription().equals(description) &&
                    catalogue.getHotelId() == hotelId && catalogue.getPrice().equals(price);
        } else {
            return false;
        }
    }
}
