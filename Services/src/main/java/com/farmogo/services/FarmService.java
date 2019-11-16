package com.farmogo.services;

import com.farmogo.dao.FarmDao;
import com.farmogo.model.Farm;
import com.farmogo.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class FarmService {

    @Inject
    FarmDao farmDao;

    public List<Farm> getFarms(User user) {
        // Todo: change to real user id
        // return farmDao.getFarmByUser(user.getUuid());
        return farmDao.getFarmByUser(null);
    }

    public Farm get(String id) {
        return farmDao.get(id);
    }
}
