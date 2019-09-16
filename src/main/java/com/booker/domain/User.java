package com.booker.domain;

import com.booker.database.impl.UserMapperImpl;

public abstract class User {
    protected int id;
    protected String username;
    protected String password;
    protected String role;

    /**
     * authenticate the username and password of an account
     * @param username username
     * @param password password
     * @return authenticated user object, return null if failed to authenticate
     */
    public static User authenticate(String username, String password) {
        UserMapperImpl mapper = new UserMapperImpl();
        return mapper.findUserByUsernamePassword(username, password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        if (username == null) {
            load();
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        if (password == null) {
            load();
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        if (role == null) {
            load();
        }
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private void load() {

    }
}
