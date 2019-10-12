package com.booker.domain;

import com.booker.database.IdentityMap;
import com.booker.database.impl.UserMapperImpl;
import com.booker.util.AppSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

public abstract class User {
    protected int id;
    protected String username;
    protected String password;
    protected String role;

    public static User getUserById(int id) {
        User user = IdentityMap.getUser(id);
        if (user == null) {
            UserMapperImpl mapper = new UserMapperImpl();
            user = mapper.findUserById(id);
        }
        return user;
    }

    public static User getUserByName(String username) {
        UserMapperImpl mapper = new UserMapperImpl();
        return mapper.findUserByUsername(username);
    }

    /**
     * authenticate the username and password of an account
     * @param username username
     * @param password password
     * @return authenticated user object, return null if failed to authenticate
     */
    public static User authenticate(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
            User user = User.getUserByName(username);
            AppSession.init(user);
            return user;
        } catch (UnknownAccountException e){
            e.printStackTrace();
            return null;
        }
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
