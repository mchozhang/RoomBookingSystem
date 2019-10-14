package com.booker.util.dataTransferObject;

import com.booker.database.impl.UserMapperImpl;
import com.booker.domain.User;
import com.booker.util.dataTransferObject.DTOAssembler;

import java.rmi.RemoteException;

public class RemoteFacade {
    public static String getUserInfoDTO(int userId) throws RemoteException {
        UserMapperImpl mapper = new UserMapperImpl();
        User user = mapper.findUserById(userId);
        return DTOAssembler.writeDTO(user).toJsonString();
    }
}
