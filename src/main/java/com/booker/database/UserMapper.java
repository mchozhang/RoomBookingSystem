package com.booker.database;

import com.booker.domain.User;

public interface UserMapper {
    User findUserByUsernamePassword(String username, String password);
    User findUserById(int id);
}
