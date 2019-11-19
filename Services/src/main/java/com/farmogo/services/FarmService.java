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

    public List<Farm> getAll(){
        return farmDao.getAll();
    }

    public List<Farm> getFarms(User user) {
        // Todo: change to real user id
        // return farmDao.getFarmByUser(user.getUuid());
         return farmDao.getAll();
    }

    public com.farmogo.model.Farm get(String id) {
        return farmDao.get(id);
    }

    public Farm save(Farm farm){
        return farmDao.save(farm);
    }

    public void delete(Farm farm) {
        farmDao.delete(farm);
    }
}
