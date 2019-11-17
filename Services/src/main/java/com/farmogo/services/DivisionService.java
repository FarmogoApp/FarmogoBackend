package com.farmogo.services;

import com.farmogo.model.Division;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DivisionService {


  //  @Inject
  //  DivisionDAO divisionsDAO;

    public List<Division> getAll() {
        return null;
      //  return divisionsDAO.getAll();
    }


}