package com.farmogo.services;

import com.farmogo.dao.FarmDao;
import com.farmogo.model.Building;
import com.farmogo.model.Division;
import com.farmogo.model.Farm;
import com.farmogo.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@Stateless
public class FarmService {

    @Inject
    FarmDao farmDao;

    @Inject
    GlobalSessionService globalSessionService;


    public List<Farm> getAll(){
        return farmDao.getAll();
    }

    public List<Farm> getFarms() {
        // Todo: change to real user id
         //return farmDao.getFarmByUser(globalSessionService.getUser().getUuid());
         return farmDao.getAll();
    }

    public void updateAnimalCounter(String id){
        farmDao.updateAnimalCounter(id);
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

    public void setCurrentFarm(Farm farm){
        globalSessionService.setFarm(farm);
    }

    public Farm getCurrentFarm(){
        Farm farm = globalSessionService.getFarm();
        if (farm == null){
            List<Farm> farms = getFarms();
            if (!farms.isEmpty()){
                farm = farms.get(0);
            }
        }
        return farm;
    }

    public List<Division> getFarmDivisions(Farm farm){
        ArrayList<Division> divisions = new ArrayList<>();

        for (Building b: farm.getBuildings()) divisions.addAll(b.getDivisions());

        return divisions;


    }

    public Division getDivisionById(String divisionId){
        List<Division> divisionList = getFarmDivisions(getCurrentFarm());

        Optional<Division> division = divisionList.stream()
                .filter(p -> p.getUuid().equals(divisionId))
                .findFirst();

        return division.orElseGet(Division::new);
    }

    public Building getBuildingContainingDivision(String divisionId){
        for (Building building: getCurrentFarm().getBuildings()) {
            for (Division division: building.getDivisions()){
                if(division.getUuid().equals(divisionId)) return building;
            }
        }
        return new Building();
    }
}
