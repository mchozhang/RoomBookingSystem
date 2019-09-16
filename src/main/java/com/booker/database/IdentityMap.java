package com.booker.database;

import com.booker.domain.*;

import java.util.HashMap;
import java.util.Map;

public class IdentityMap {
    private static Map<Integer, Hotel> hotelMap = new HashMap<>();
    private static Map<Integer, User> userMap = new HashMap<>();
    private static Map<Integer, Catalogue> catalogueMap = new HashMap<>();
    private static Map<Integer, Room> roomMap = new HashMap<>();
    private static Map<Integer, Service> serviceMap = new HashMap<>();

    public static Hotel getHotel(int id) {
        return hotelMap.get(id);
    }

    public static User getUser(int id) {
        return userMap.get(id);
    }

    public static Catalogue getCatalogue(int id) {
        return catalogueMap.get(id);
    }

    public static Room getRoom(int id) {
        return roomMap.get(id);
    }

    public static Service getService(int id) {
        return serviceMap.get(id);
    }

    public static void putUser(int id, User user) {
        userMap.put(id, user);
    }

    public static void putHotel(int id, Hotel hotel) {
        hotelMap.put(id, hotel);
    }

    public static void putCatalogue(int id, Catalogue catalogue) {
        catalogueMap.put(id, catalogue);
    }

    public static void putService(int id, Service service) {
        serviceMap.put(id, service);
    }

    public static void putRoom(int id, Room room) {
        roomMap.put(id, room);
    }
}
