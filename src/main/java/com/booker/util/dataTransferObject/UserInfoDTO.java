package com.booker.util.dataTransferObject;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * A UserInfoDTO allow clients to obtain user relevant data in the form of JSON.
 * a customer DTO include information of full name and all the bookings,
 * a staff DTO include information of hotel name, catalogues and all the bookings.
 */
public class UserInfoDTO {
    private String name;
    private List<CatalogueDTO> catalogues;
    private List<BookingDTO> bookings;

    /**
     * serialize userInfoDTO to json
     * @return json string
     */
    public String toJsonString() {
        JSONObject object = new JSONObject();
        object.put("name", this.name);

        if (catalogues != null) {
            JSONArray array = new JSONArray();
            for (CatalogueDTO catalogue: catalogues) {
                array.put(catalogue.toJsonObject());
            }
            object.put("catalogues", array);
        }

        // bookings
        JSONArray array = new JSONArray();
        for (BookingDTO booking: bookings) {
            array.put(booking.toJsonObject());
        }
        object.put("bookings", array);
        return object.toString();
    }

    /**
     * deserialize the json object
     * @param json DTO json string
     * @return userInfoDTO object
     */
    public UserInfoDTO fromJsonString(String json) {
        JSONObject object = new JSONObject(json);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName(object.getString("name"));

        // deserialize catalogues
        JSONArray catalogues = object.getJSONArray("catalogues");
        List<CatalogueDTO> catalogueDTOS = new ArrayList<>();
        if (catalogues != null) {
            for (Object jsonObject: catalogues){
                catalogueDTOS.add(CatalogueDTO.fromJsonObject ((JSONObject)jsonObject));
            }
            userInfoDTO.setCatalogues(catalogueDTOS);
        }

        // deserialize bookings
        JSONArray bookings = object.getJSONArray("bookings");
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for (Object jsonObject: bookings){
            bookingDTOS.add(BookingDTO.fromJsonObject((JSONObject)jsonObject));
        }
        userInfoDTO.setBookings(bookingDTOS);

        return userInfoDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }

    public List<CatalogueDTO> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(List<CatalogueDTO> catalogues) {
        this.catalogues = catalogues;
    }
}
