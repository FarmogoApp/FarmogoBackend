package com.farmogo.services;


import com.farmogo.model.Races;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RacesService {

    /*
    @Inject
    RacesDAO racesDAO;
*/
    public List<Races> getAll() {
        return null;
        //return racesDAO.getAll();
    }


}