package com.booker.domain;

public abstract class BookerObj {
    protected int id;
    protected int version;

    public BookerObj() {
        version = 1;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
