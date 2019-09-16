package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.UnitOfWork;
import com.booker.database.impl.CatalogueMapperImpl;
import com.booker.database.impl.HotelMapperImpl;
import com.booker.database.impl.ServiceMapperImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private Location location;

    /**
     * get the list of all the hotels in the system
     *
     * @return list of hotel
     */
    public static List<Hotel> getHotelList() {
        HotelMapperImpl hotelMapper = new HotelMapperImpl();
        return hotelMapper.findAllHotels();
    }

    /**
     * find hotel by hotel id
     *
     * @param id hotel id
     * @return hotel object
     */
    public static Hotel getHotelById(int id) {
        Hotel hotel = IdentityMap.getHotel(id);
        if (hotel == null) {
            HotelMapperImpl hotelMapper = new HotelMapperImpl();
            hotel = hotelMapper.findHotelById(id);
        }
        return hotel;
    }

    public Hotel(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Hotel(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;

        IdentityMap.putHotel(id, this);
    }

    /**
     * get the list of service of this hotel
     *
     * @return list of service
     */
    public List<Service> getServices() {
        ServiceMapperImpl mapper = new ServiceMapperImpl();
        return mapper.findServicesByHotelId(this.id);
    }

    /**
     * update the services of the hotel
     *
     * @param servicesStr services string, split by comma
     */
    public void setServices(String servicesStr) {
        ServiceMapperImpl mapper = new ServiceMapperImpl();
        String[] servicesArray = servicesStr.split(",");
        List<Service> originalServices = getServices();
        List<String> services = Arrays.asList(servicesArray);
        List<String> originalServiceNames = new ArrayList<>();
        for (Service service: originalServices) {
            originalServiceNames.add(service.getName());
        }

        // remove service-hotel map
        for (Service originalService: originalServices) {
            if (!services.contains(originalService.getName())) {
                mapper.removeHotelServiceMap(id, originalService.getId());
            }
        }

        // add service-hotel map
        for (String service: services) {
            if (!originalServiceNames.contains(service)) {
                mapper.addHotelServiceMap(id, service);
            }
        }
    }

    /**
     * get the list of room catalogues of this hotel
     *
     * @return list of catalogue
     */
    public List<Catalogue> getCatalogues() {
        CatalogueMapperImpl catalogueMapper = new CatalogueMapperImpl();
        return catalogueMapper.findCataloguesByHotelId(this.id);
    }

    public int getId() {
        return id;
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

    public Location getLocation() {
        if (location == null) {
            load();
        }
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSuburb() {
        if (location == null) {
            load();
        }
        return this.location.getSuburb();
    }

    public String getAddress() {
        if (location == null) {
            load();
        }
        return this.location.getAddress();
    }

    @Override
    public String toString() {
        return String.format("Hotel[id: %d, name: %s, suburb: %s, address: %s]",
                this.id, this.name, this.location.getSuburb(), this.location.getAddress());
    }

    private void load() {
        try {
            HotelMapperImpl mapper = new HotelMapperImpl();
            ResultSet resultSet = mapper.selectRowById(id);
            name = resultSet.getString("name");
            String suburb = resultSet.getString("suburb");
            String address = resultSet.getString("address");
            this.location = new Location(suburb, address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hotel) {
            Hotel hotel = (Hotel) obj;
            return hotel.getId() == id && hotel.getName().equals(name) && hotel.getLocation().equals(location);
        } else {
            return false;
        }
    }
}
