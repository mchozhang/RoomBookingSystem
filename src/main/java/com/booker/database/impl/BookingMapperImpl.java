package com.booker.database.impl;

import com.booker.database.DataMapper;
import com.booker.database.QueryExecutor;
import com.booker.domain.BookerObj;
import com.booker.domain.Booking;
import com.booker.domain.Hotel;
import com.booker.util.DateUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapperImpl implements DataMapper {

    private QueryExecutor executor;

    public BookingMapperImpl() {
        executor = QueryExecutor.getInstance();
    }

    public ResultSet selectRowById(int id) {
        try {
            String sql = "select * from bookings where id = ?";
            return executor.getResultSet(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Booking> findBookingsByUserId(int userId) {
        try {
            String sql = "select * from bookings where userId = ?";

            ResultSet rs = executor.getResultSet(sql, userId);
            List<Booking> bookings = new ArrayList<>();
            while (rs.next()) {
                bookings.add(createEntity(rs));
            }
            return bookings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Booking> findBookingsByHotelId(int hotelId) {
        try {
            Hotel hotel = Hotel.getHotelById(hotelId);
            String sql = "select * from bookings";

            ResultSet rs = executor.getResultSet(sql);
            List<Booking> bookings = new ArrayList<>();
            while (rs.next()) {
                Booking booking = createEntity(rs);
                if (booking.getHotelName().equals(hotel.getName())){
                    bookings.add(booking);
                }
            }
            return bookings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Booking> findBookingByRoomWithinDate(int roomId, Date starDate, Date endDate) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bookings WHERE " +
                    "roomId = ? AND (" +
                    "(startDate <= ? AND endDate >= ?) OR " +
                    "(startDate <= ? AND endDate >= ?) OR " +
                    "(startDate >= ? AND endDate <= ?))";
            ResultSet rs = executor.getResultSet(sql, roomId, starDate, endDate, starDate, endDate, starDate, endDate);
            while (rs.next()) {
                bookings.add(createEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    private Booking createEntity(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            int userId = rs.getInt("userId");
            int roomId = rs.getInt("roomId");
            float price = rs.getFloat("price");
            String status = rs.getString("status");
            Date startDate = rs.getDate("startDate");
            Date endDate = rs.getDate("endDate");
            int version = rs.getInt("version");
            if (DateUtil.getToday().compareTo(endDate.toString()) > 0) {
                status = "Finished";
                bookingFinished(id);
            }
            return new Booking(id, userId, roomId, price, startDate, endDate, status, version);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void bookingFinished(int id) {
        String sql = "update bookings set status = ? where id = ?";
        executor.executeStatement(sql, "Finished", id);

    }

    public int insert(BookerObj obj) {
        Booking booking = (Booking) obj;
        String sql = "insert into bookings (userId, roomId, price, startDate, endDate, status, version) values (?,?,?,?,?,?,?)";
        return executor.executeStatement(sql, booking.getUserId(), booking.getRoomId(), booking.getPrice(),
                booking.getStartDate(), booking.getEndDate(), booking.getStatus(), booking.getVersion());
    }

    public int update(BookerObj obj) {
        Booking booking = (Booking) obj;
        String sql = "update bookings set version = ? ,startDate = ?, endDate = ?, price = ?, status = ? where id = ?";
        return executor.executeStatement(sql, booking.getVersion(), booking.getStartDate(), booking.getEndDate(), booking.getPrice(), booking.getStatus(), booking.getId());
    }

    public void delete(BookerObj obj) {
        Booking booking = (Booking) obj;
        String sql = "delete from bookings where id = ?";
        executor.executeStatement(sql, booking.getId());
    }
}
