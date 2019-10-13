package com.booker.util;

import com.booker.domain.*;

import java.util.ArrayList;
import java.util.List;

public class DTOAssembler {
    public static UserInfoDTO writeDTO(User user) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        List<Booking> bookingList = null;

        String name = "";
        if (user instanceof Customer){
            name = ((Customer) user).getFullName();
            bookingList = Booking.getCustomerBookings(user.getId());
        } else if (user instanceof Staff) {
            Staff staff = (Staff)user;
            name = staff.getHotelName();
            bookingList = Booking.getHotelBookings(staff.getHotelId());

            // set catalogue DTO
            Hotel hotel = Hotel.getHotelById(staff.getHotelId());
            List<CatalogueDTO> catalogueDTOs = new ArrayList<>();
            for (Catalogue catalogue: hotel.getCatalogues()) {
                CatalogueDTO dto = new CatalogueDTO();
                dto.setName(catalogue.getName());
                dto.setDescription(catalogue.getDescription());
                dto.setPrice(catalogue.getPrice().toString());
                catalogueDTOs.add(dto);
            }
            userInfoDTO.setCatalogues(catalogueDTOs);
        }

        // set bookings DTO
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking: bookingList) {
            BookingDTO dto = new BookingDTO();
            dto.setHotelName(booking.getHotelName());
            dto.setCatalogueName(booking.getCatalogueName());
            dto.setCustomerName(booking.getUserFullName());
            dto.setPrice(booking.getPrice().toString());
            dto.setRoomNumber(booking.getRoomNumber());
            bookingDTOs.add(dto);
        }
        userInfoDTO.setBookings(bookingDTOs);
        userInfoDTO.setName(name);

        return userInfoDTO;
    }
}
