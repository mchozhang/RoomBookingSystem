package com.booker.util;

import com.booker.database.impl.UserMapperImpl;
import com.booker.domain.User;

import java.rmi.RemoteException;

public class RemoteFacade {
    public static String getUserInfoDTO(int userId) throws RemoteException {
        UserMapperImpl mapper = new UserMapperImpl();
        User user = mapper.findUserById(userId);
        return DTOAssembler.writeDTO(user).toJsonString();
    }
}
