package com.booker.domain;

import com.sun.org.apache.xalan.internal.xsltc.dom.AdaptiveResultTreeImpl;

public class Location {
    private String suburb;
    private String address;

    public Location(String suburb, String address) {
        this.suburb = suburb;
        this.address = address;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location location = (Location) obj;
            return location.getAddress().equals(address) && location.getSuburb().equals(suburb);
        } else {
            return false;
        }
    }
}
