package com.booker.domain;

import com.booker.database.UserMapper;
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
        UserMapper mapper = new UserMapperImpl();
        User user = mapper.findUserByUsernamePassword(username, password);
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
