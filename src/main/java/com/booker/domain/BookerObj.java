package com.booker.domain;

public abstract class BookerObj {
    public static final int NOT_ASSIGNED = 0;
    protected int id;
    protected int version;

    public BookerObj() {
        id = NOT_ASSIGNED;
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
