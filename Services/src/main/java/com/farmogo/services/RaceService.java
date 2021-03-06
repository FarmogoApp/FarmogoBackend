package com.farmogo.services;


import com.farmogo.dao.RaceDao;
import com.farmogo.model.Animal;
import com.farmogo.model.Race;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RaceService {

    @Inject
    RaceDao raceDAO;

    @Inject
    AnimalService animalService;

    @Inject
    NotificationService notificationService;

    public List<Race> getAll() {
        return raceDAO.getAll();
    }

    public Race get(String id) { return raceDAO.get(id); }

    public Race save(Race race){
        Race save = raceDAO.save(race);
        notificationService.sendNotificationToUpdate("race");
        return save;



    }

    public void delete(Race race) throws HasRelationatedDataException {
        List<Animal> animals = animalService.getByRace(race.getUuid());
        if (!animals.isEmpty()){
            throw new HasRelationatedDataException();
        }
        raceDAO.delete(race);
        notificationService.sendNotificationToUpdate("race");
    }
}