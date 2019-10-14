package com.booker.util.dataTransferObject;

import org.json.JSONObject;

public class CatalogueDTO {
    private String name;
    private String description;
    private String price;

    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("description", description);
        object.put("price", price);
        return object;
    }

    public String toJsonString() {
        return this.toJsonObject().toString();
    }

    public static CatalogueDTO fromJsonObject(JSONObject jsonObject) {
        CatalogueDTO dto = new CatalogueDTO();
        dto.setPrice(jsonObject.getString("price"));
        dto.setName(jsonObject.getString("name"));
        dto.setDescription(jsonObject.getString("description"));
        return dto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
