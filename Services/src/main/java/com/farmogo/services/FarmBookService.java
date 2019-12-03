package com.farmogo.services;


import com.farmogo.dao.FarmBookDao;
import com.farmogo.model.FarmBook;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class FarmBookService {

    @Inject
    FarmBookDao farmBookDao;


    public List<FarmBook> getAll() {
        return farmBookDao.getAll();
    }

    public FarmBook get(String id) {
        return farmBookDao.get(id);
    }

    public FarmBook save(FarmBook farmBook){
        return farmBookDao.save(farmBook);
    }

    public void delete(FarmBook farmBook) {
        farmBookDao.delete(farmBookDao.get(farmBook.getUuid()));
    }
}


