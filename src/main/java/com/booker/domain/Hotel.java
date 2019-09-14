package com.booker.domain;

import com.booker.database.CatalogueMapper;
import com.booker.database.HotelMapper;
import com.booker.database.ServiceMapper;
import com.booker.database.impl.CatalogueMapperImpl;
import com.booker.database.impl.HotelMapperImpl;
import com.booker.database.impl.ServiceMapperImpl;

import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private Location location;

    /**
     * get the list of all the hotels in the system
     * @return list of hotel
     */
    public static List<Hotel> getHotelList() {
        HotelMapper hotelMapper = new HotelMapperImpl();
        List<Hotel> hotels =  hotelMapper.findAllHotels();
        return hotels;
    }

    /**
     * find hotel by hotel id
     * @param id hotel id
     * @return hotel object
     */
    public static Hotel getHotelById(int id) {
        HotelMapper hotelMapper = new HotelMapperImpl();
        Hotel hotel = hotelMapper.findHotelById(id);
        return hotel;
    }

    /**
     * get the list of service of this hotel
     * @return list of service
     */
    public List<Service> getServices() {
        ServiceMapper mapper = new ServiceMapperImpl();
        return mapper.findServicesByHotelId(this.id);
    }

    /**
     * get the list of room catalogues of this hotel
     * @return list of catalogue
     */
    public List<Catalogue> getCatalogues() {
        CatalogueMapper catalogueMapper = new CatalogueMapperImpl();
        return catalogueMapper.findCataloguesByHotelId(this.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSuburb() {
        return this.location.getSuburb();
    }

    public String getAddress() {
        return this.location.getAddress();
    }

    @Override
    public String toString() {
        return String.format("Hotel[id: %d, name: %s, suburb: %s, address: %s]",
                this.id, this.name, this.location.getSuburb(), this.location.getAddress());
    }
}
