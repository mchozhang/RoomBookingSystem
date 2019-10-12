package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.impl.BookingMapperImpl;
import com.booker.util.DateUtil;

import java.sql.Date;
import java.util.List;


public class Booking {
    private int id;
    private int userId;
    private int roomId;
    private Float price;
    private Date startDate;
    private Date endDate;
    private String status;

    private String hotelName;
    private String catalogueName;
    private String roomNumber;



    private String userFullName;

    public Booking(int userId, int roomId, Float price, Date startDate, Date endDate) {
        this.userId = userId;
        this.roomId = roomId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "To be confirmed";
    }

    public Booking(int id, int userId, int roomId, Float price, Date startDate, Date endDate, String status) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;

        Customer user = (Customer)Customer.getUserBy(userId);
        Room room = Room.getRoomById(roomId);
        Catalogue catalogue = Catalogue.getCatalogueById(room.getCatalogueId());
        Hotel hotel = Hotel.getHotelById(catalogue.getHotelId());
        this.hotelName = hotel.getName();
        this.catalogueName = catalogue.getName();
        this.roomNumber = room.getNumber();
        this.userFullName = user.getFullName();
        IdentityMap.putBooking(id, this);
    }

    public static List<Booking> getCustomerBookings(int userId) {
        BookingMapperImpl mapper = new BookingMapperImpl();
        return mapper.findBookingsByUserId(userId);
    }

    public static List<Booking> getHotelBookings(int hotelId) {
        BookingMapperImpl mapper = new BookingMapperImpl();
        return mapper.findBookingsByHotelId(hotelId);
    }

    public static boolean createBooking(int userId, int catalogueId, int roomId, String startDateStr, String endDateStr) {
        try {
            Date startDate = Date.valueOf(startDateStr);
            Date endDate = Date.valueOf(endDateStr);
            Catalogue catalogue = Catalogue.getCatalogueById(catalogueId);
            Float price = catalogue.getPrice() * DateUtil.dayDifference(startDateStr, endDateStr);
            BookingMapperImpl mapper = new BookingMapperImpl();
            mapper.insert(new Booking(userId, roomId, price, startDate, endDate));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogueName = catalogueName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getDateRange() {
        return startDate.toString() + " - " + endDate.toString();
    }
}
