package com.farmogo.services;

import com.farmogo.model.Divisions;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DivisionService {


  //  @Inject
  //  DivisionDAO divisionsDAO;

    public List<Divisions> getAll() {
        return null;
      //  return divisionsDAO.getAll();
    }


}