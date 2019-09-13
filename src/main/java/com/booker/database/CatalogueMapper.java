package com.booker.database;

import com.booker.domain.Catalogue;

import java.util.List;

public interface CatalogueMapper {
    List<Catalogue> findCataloguesByHotelId(int id);
}
