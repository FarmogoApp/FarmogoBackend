package com.farmogo.front;

import com.farmogo.model.AnimalCounter;
import com.farmogo.model.Building;
import com.farmogo.model.Division;
import com.farmogo.model.Farm;
import com.farmogo.services.BuildingService;
import com.farmogo.services.FarmService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class ConfigView implements Serializable {

    @Inject
    FarmService farmService;

    @Inject
    BuildingService buildingService;

    private Farm farm;
    private List<Building> buildingsList;
    private Building building;

    @PostConstruct
    public void init() {
        farm = farmService.getCurrentFarm();
        buildingsList = farm.getBuildings();
        building = buildingsList.get(0);
    }

    public void clearBuildingSelection() {
        building = new Building();
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Building> getBuildings() {
        return buildingsList;
    }

    public AnimalCounter getAnimalCounter() {
        return farm.getAnimalCounter();
    }

    public void setAnimalCounter(AnimalCounter animalCounter) {
        this.farm.setAnimalCounter(animalCounter);
    }

    public Farm getFarm() {
        return farm;
    }

    public void saveBuilding() {
        //this.building.setUuid("8888");
        Division d11 = new Division();
        d11.setName("division 1.1");
        //d11.setUuid("5555");

        this.building.setDivisions(Arrays.asList(d11));

        //farm.setBuildings(buildingsList);
        farm.getBuildings().add(this.building);
        farm = farmService.save(farm);
        buildingsList = farm.getBuildings();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", farm.getAnimalCounter().toString()) );
    }

    public void save() {
        farmService.save(farm);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", farm.getAnimalCounter().toString()) );
    }
}
