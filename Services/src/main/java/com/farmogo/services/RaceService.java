package com.farmogo.services;


import com.farmogo.dao.RaceDao;
import com.farmogo.model.Race;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RaceService {

    @Inject
    RaceDao raceDAO;

    public List<Race> getAll() {
        return raceDAO.getAll();
    }

    public Race save(Race race){
        return raceDAO.save(race);
    }

    public void delete(Race race) {
        Race raceToDelete = raceDAO.get(race.getUuid());
        raceDAO.delete(raceToDelete);
    }

}