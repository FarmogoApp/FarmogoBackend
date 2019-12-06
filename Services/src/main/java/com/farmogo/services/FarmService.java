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
import java.util.stream.Collectors;

@Stateless
public class FarmService {

    @Inject
    FarmDao farmDao;

    @Inject
    GlobalSessionService globalSessionService;

    @Inject
    UserService userService;


    public List<Farm> getAll() {
        return farmDao.getAll();
    }

    public List<Farm> getFarms() {
        return globalSessionService.getUser().getFarmsAccessible().stream().
                map(f -> farmDao.get(f)).collect(Collectors.toList());
    }

    public List<Farm> getFarmsOwned(){
        return farmDao.getFarmByOwner(globalSessionService.getUser().getUuid());
    }

    public Farm get(String id) {
        return farmDao.get(id);
    }

    public Farm save(Farm farm) {
        boolean isNew = farm.getUuid() == null;

        if (isNew)
            farm.setUserOwnerId(globalSessionService.getUser().getUuid());

        Farm newFarm = farmDao.save(farm);

        if (isNew) {
            User user = globalSessionService.getUser();
            ArrayList<String> farmsAccessibles;
            if (user.getFarmsAccessible() != null) {
                farmsAccessibles = new ArrayList<>(user.getFarmsAccessible());
            } else {
                farmsAccessibles = new ArrayList<>();
            }
            farmsAccessibles.add(newFarm.getUuid());
            user.setFarmsAccessible(farmsAccessibles);
            userService.save(user);
        }

        return newFarm;
    }

    public void delete(Farm farm) {
        farmDao.delete(farm);
    }

    public Farm getCurrentFarm() {
        Farm farm = globalSessionService.getFarm();
        if (farm == null) {
            List<Farm> farms = getFarms();
            if (!farms.isEmpty()) {
                farm = farms.get(0);
            }
        }
        return farm;
    }

    public void setCurrentFarm(Farm farm) {
        globalSessionService.setFarm(farm);
    }

    public List<Division> getFarmDivisions(Farm farm) {
        ArrayList<Division> divisions = new ArrayList<>();

        for (Building b : farm.getBuildings()) divisions.addAll(b.getDivisions());

        return divisions;


    }

    public Division getDivisionById(String divisionId) {
        List<Division> divisionList = getFarmDivisions(getCurrentFarm());

        Optional<Division> division = divisionList.stream()
                .filter(p -> p.getUuid().equals(divisionId))
                .findFirst();

        return division.orElseGet(Division::new);
    }

    public Building getBuildingContainingDivision(String divisionId) {
        for (Building building : getCurrentFarm().getBuildings()) {
            for (Division division : building.getDivisions()) {
                if (division.getUuid().equals(divisionId)) return building;
            }
        }
        return new Building();
    }
}
