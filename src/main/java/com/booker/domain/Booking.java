package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.LockingMapper;
import com.booker.database.UnitOfWork;
import com.booker.database.impl.BookingMapperImpl;
import com.booker.util.DateUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Booking extends BookerObj {
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

    public Booking(int userId, int roomId, Date startDate, Date endDate) {
        this.userId = userId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "To be confirmed";
    }

    public Booking(int userId, int roomId, Float price, Date startDate, Date endDate) {
        super();
        this.userId = userId;
        this.roomId = roomId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "To be confirmed";
    }

    public Booking(int id, int userId, int roomId, Float price, Date startDate, Date endDate, String status, int version) {
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.version = version;

        Customer user = (Customer) Customer.getUserById(userId);
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
        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Catalogue catalogue = Catalogue.getCatalogueById(catalogueId);
        Float price = catalogue.getPrice() * DateUtil.dayDifference(startDateStr, endDateStr);
        return Booking.createBooking(new Booking(userId, roomId, price, startDate, endDate));
    }

    public static boolean createBooking(Booking booking) {
        try {
            if (booking.hasDateConflict()) {
                return false;
            }
            UnitOfWork.getInstance().registerNew(booking);
            UnitOfWork.getInstance().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * whether a new booking has date conflict with other bookings
     */
    public boolean hasDateConflict() {
        return hasDateConflict(getStartDate(), getEndDate());
    }

    public boolean hasDateConflict(Date startDate, Date endDate) {
        BookingMapperImpl mapper = new BookingMapperImpl();
        List<Booking> bookings =
                mapper.findBookingByRoomWithinDate(getRoomId(), startDate, endDate);
        if (getId() == NOT_ASSIGNED) {
            return bookings.size() > 0;
        } else {
            if (bookings.size() == 0) {
                return false;
            }
            return !(bookings.size() == 1 && bookings.get(0).getId() == id);
        }
    }

    public static boolean confirmBooking(int bookingId) {
        Booking booking = IdentityMap.getBooking(bookingId);
        try {
            if (booking.getStatus().equals("To be confirmed")) {
                booking.setStatus("Confirmed");
                LockingMapper mapper = new LockingMapper(new BookingMapperImpl());
                mapper.update(booking);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteBooking(int bookingId) {
        Booking booking = IdentityMap.getBooking(bookingId);
        try {
            LockingMapper mapper = new LockingMapper(new BookingMapperImpl());
            mapper.delete(booking);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateBooking(int bookingId, String start, String end) {
        Booking booking = IdentityMap.getBooking(bookingId);
        Room room = IdentityMap.getRoom(booking.getRoomId());
        Catalogue catalogue = IdentityMap.getCatalogue(room.getCatalogueId());
        try {
            Date startDate = Date.valueOf(start);
            Date endDate = Date.valueOf(end);
            if (booking.hasDateConflict(startDate, endDate)) {
                return false;
            }
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
            booking.setStatus("To be confirmed");
            booking.setPrice(catalogue.getPrice() * DateUtil.dayDifference(start, end));
            LockingMapper mapper = new LockingMapper(new BookingMapperImpl());
            mapper.update(booking);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasStarted() {
        String date = Date.valueOf(LocalDateTime.now().toLocalDate()).toString();
        return startDate.toString().compareTo(date) < 0;
    }

    public boolean hasFinished() {
        String date = Date.valueOf(LocalDateTime.now().toLocalDate()).toString();
        return endDate.toString().compareTo(date) < 0;
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
