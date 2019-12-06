package com.farmogo.front;

import com.farmogo.model.*;
import com.farmogo.services.BuildingService;
import com.farmogo.services.FarmService;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class ConfigView implements Serializable {

    @Inject
    FarmService farmService;

    @Inject
    FarmSwitcherView farmSwitcherView;

    @Inject
    BuildingService buildingService;

    private Farm farm;
    private List<Building> buildingsList;
    private Building building;
    private Division division;
    private List<Division> divisionList;
    private List<Farm> farmList;

    @PostConstruct
    public void init() {
        farmList = farmService.getFarmsOwned();
        if (!farmList.isEmpty()) {
            farm = farmList.get(0);
            buildingsList = farm.getBuildings();
            building = buildingsList.get(0);
            divisionList = building.getDivisions();
            division = divisionList.get(0);
        }
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

    public void setFarm(Farm farm){
        this.farm = farm;
        buildingsList = farm.getBuildings();
        this.building = buildingsList.get(0);
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
        FacesContext context = FacesContext.getCurrentInstance();
        //System.out.println(name);
        //setFarm(name, officialId, prefix, counter);
        clearBuildingSelection();
        clearDivisionSelection();
        division.setName("division 1.1");
        building.setDivisions(Arrays.asList(division));
        building.setName("Build 1.1");
        farm.setBuildings(Arrays.asList(building));

        try {
            farm = farmService.save(farm);
            farmList = farmService.getFarms();
            buildingsList = farm.getBuildings();
            building = buildingsList.get(0);
            division = building.getDivisions().get(0);
            farmSwitcherView.init();
            farmSwitcherView.setFarm(farm);
            context.addMessage(null, new FacesMessage("New Farm Created") );
        } catch (ModificationNotAllowed modificationNotAllowed) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Can't save Farm","") );
        }




    }

    public void saveBuilding() {
        FacesContext context = FacesContext.getCurrentInstance();
        Division d11 = new Division();
        d11.setName("division 1.1");

        this.building.setDivisions(Arrays.asList(d11));
        farm.getBuildings().add(this.building);
        try {
            farm = farmService.save(farm);
            buildingsList = farm.getBuildings();
            divisionList = building.getDivisions();
            building = buildingsList.get(buildingsList.size()-1);
            division = building.getDivisions().get(0);
            context.addMessage(null, new FacesMessage("Building saved"));
        } catch (ModificationNotAllowed modificationNotAllowed) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Can't save Farm","") );
        }
    }

    public void saveDivision() {
        FacesContext context = FacesContext.getCurrentInstance();
        this.divisionList = this.building.getDivisions();
        divisionList.add(division);
        this.building.setDivisions(divisionList);
        buildingsList = new ArrayList<>();
        buildingsList.add(this.building);

        for(Building b : farm.getBuildings() ){
            /*if (!this.building.equals(b)) {
                buildingsList.add(b);
            }*/
            if (this.building.getName().equals(b.getName())) {
            }else{
                buildingsList.add(b);
            }
        }
        farm.setBuildings(buildingsList);
        try {
            farm = farmService.save(farm);
            farmService.setCurrentFarm(farm);

            buildingsList = farm.getBuildings();

            divisionList = building.getDivisions();
            division = divisionList.get(divisionList.size()-1);
            farmList = farmService.getFarms();
            context.addMessage(null, new FacesMessage("Division saved"));
        } catch (ModificationNotAllowed modificationNotAllowed) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Can't save Farm","") );
        }





    }

    public void save() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            farm = farmService.save(farm);
            farmSwitcherView.init();
            farmSwitcherView.setFarm(farm);
            context.addMessage(null, new FacesMessage("Farm saved") );
        } catch (ModificationNotAllowed modificationNotAllowed) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Can't save Farm","") );
        }
    }

    public List<Farm> getFarmList() {
        return farmList;
    }
}