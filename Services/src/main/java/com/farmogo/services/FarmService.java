package com.farmogo.services;

import com.farmogo.dao.FarmDao;
import com.farmogo.model.*;

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

    @Inject
    AnimalService animalService;


    public List<Farm> getFarms() {
        return globalSessionService.getUser().getFarmsAccessible().stream().
                map(f -> farmDao.get(f)).collect(Collectors.toList());
    }

    public List<Farm> getFarmsOwned(){
        return farmDao.getFarmByOwner(globalSessionService.getUser().getUuid());
    }

    public Farm get(String id) throws AccessNotAllowed {
        Farm farm = farmDao.get(id);
        if (farm == null) return null;
        if (globalSessionService.getUser().getFarmsAccessible().contains(farm.getUuid())) {
            return farm;
        }else{
            throw new AccessNotAllowed();
        }

    }

    public Farm save(Farm farm) throws ModificationNotAllowed {
        boolean isNew = farm.getUuid() == null;

        if (isNew)
            farm.setUserOwnerId(globalSessionService.getUser().getUuid());
        else{
            if (globalSessionService.getUser() == null ||
                    !globalSessionService.getUser().getUuid().equals(farm.getUserOwnerId())){
                throw new ModificationNotAllowed();
            }
        }

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

    public void delete(Farm farm) throws DeleteNotAllowed, DeleteNotPossible {
        if (globalSessionService.getUser() == null ||
                !globalSessionService.getUser().getUuid().equals(farm.getUserOwnerId())){
            throw new DeleteNotAllowed();
        }
        List<Animal> animalsByFarmId = animalService.getAnimalsByFarmId(farm.getUuid());
        if (!animalsByFarmId.isEmpty()){
            throw new DeleteNotPossible();
        }

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
