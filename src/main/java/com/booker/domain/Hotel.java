package com.booker.domain;

public class Hotel {
    private int id;
    private String name;
    private Location location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSuburb() {
        return this.location.getSuburb();
    }

    public String getAddress() {
        return this.location.getAddress();
    }

    @Override
    public String toString() {
        return String.format("Hotel[id: %d, name: %s, suburb: %s, address: %s]",
                this.id, this.name, this.location.getSuburb(), this.location.getAddress());
    }
}
