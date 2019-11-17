package com.farmogo.services;

import com.farmogo.model.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserService {
    /*
    @Inject
    UserDAO userDAO;
*/

    public List<User> getAll() {
        return null;
        //return userDAO.getAll();
    }


}

