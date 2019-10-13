package com.booker.util;

import org.json.JSONObject;

public class BookingDTO {
    private String hotelName;
    private String catalogueName;
    private String roomNumber;
    private String price;
    private String dateRange;
    private String customerName;

    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("hotelName", hotelName);
        object.put("catalogueName", catalogueName);
        object.put("roomNumber", roomNumber);
        object.put("price", price);
        object.put("dateRange", dateRange);
        object.put("customerName", customerName);
        return object;
    }

    public String toJsonString() {
        return this.toJsonObject().toString();
    }

    public static BookingDTO fromJsonObject(JSONObject jsonObject) {
        BookingDTO dto = new BookingDTO();
        dto.setHotelName(jsonObject.getString("hotelName"));
        dto.setCatalogueName(jsonObject.getString("catalogueName"));
        dto.setRoomNumber(jsonObject.getString("roomNumber"));
        dto.setPrice(jsonObject.getString("price"));
        dto.setDateRange(jsonObject.getString("dateRange"));
        dto.setCustomerName(jsonObject.getString("customerName"));
        return dto;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
