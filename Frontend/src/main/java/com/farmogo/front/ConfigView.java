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
    private Division division;
    private List<Division> divisionList;

    @PostConstruct
    public void init() {
        farm = farmService.getCurrentFarm();
        buildingsList = farm.getBuildings();
        building = buildingsList.get(0);
    }

    public void clearFarmSelection() {
        farm = new Farm();
    }

    public void clearBuildingSelection() {
        building = new Building();
    }

    public void clearDivisionSelection() {
        division = new Division();
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

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public List<Division> getDivisionList() {
        divisionList = this.building.getDivisions();
        return divisionList;
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

    public void saveNewFarm() {
        clearBuildingSelection();
        clearDivisionSelection();
        division.setName("division 1.1");
        building.setDivisions(Arrays.asList(division));
        farm.setBuildings(Arrays.asList(building));
        farmService.save(farm);
        buildingsList = farm.getBuildings();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", farm.getAnimalCounter().toString()) );
    }

    public void saveBuilding() {
        Division d11 = new Division();
        d11.setName("division 1.1");

        this.building.setDivisions(Arrays.asList(d11));
        farm.getBuildings().add(this.building);
        farm = farmService.save(farm);
        buildingsList = farm.getBuildings();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", farm.getAnimalCounter().toString()) );
    }

    public void saveDivision() {
        divisionList = this.building.getDivisions();
        divisionList.add(division);
        this.building.setDivisions(divisionList);

        farm = farmService.save(farm);
        buildingsList = farm.getBuildings();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", farm.getAnimalCounter().toString()) );
    }

    public void save() {
        farmService.save(farm);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Farm saved") );
    }
}
