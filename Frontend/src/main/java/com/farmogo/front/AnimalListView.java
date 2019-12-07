package com.farmogo.front;

import com.farmogo.model.*;
import com.farmogo.services.AnimalService;
import com.farmogo.services.AnimalTypesService;
import com.farmogo.services.FarmService;
import com.farmogo.services.RaceService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class AnimalListView implements Serializable {

    @Inject
    FarmService farmService;

    @Inject
    AnimalService animalService;

    @Inject
    RaceService raceService;

    @Inject
    AnimalTypesService animalTypesService;


    private Farm farm;
    private Animal animal;
    private Animal mother;
    private String motherOfficialId;

    private List<Animal> animalList;
    private Map<String, String> races = new HashMap<>();
    private Map<String, String> animalTypes = new HashMap<>();
    private Map<String, String> divisions = new HashMap<>();
    private FilterAnimal filter;
    private List<Animal> mothers;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        if (params.containsKey("filter")){
            filter = FilterAnimal.valueOf(params.get("filter"));
        }else{
            filter =  FilterAnimal.Current;
        }

        farm = farmService.getCurrentFarm();
        if (farm != null) {
            loadAnimals();
        }
        animal = new Animal();
        if (farm!=null) {
            mothers = animalService.getCurrentAnimalsByFarmId(farm.getUuid()).stream()
                    .filter(p -> p.getSex().equals("Female"))
                    .collect(Collectors.toList());
        }
    }

    public void loadAnimals() {
        switch (filter) {
            case All:
                animalList = animalService.getAnimalsByFarmId(farm.getUuid());
                break;
            case Current:
                animalList = animalService.getCurrentAnimalsByFarmId(farm.getUuid());
                break;
            case Discharged:
                animalList = animalService.getDischagedAnimalsByFarmId(farm.getUuid());
                break;
        }
        HashRaces();
        HashAnimalTypes();
        HashDivisions();
    }

    private void HashDivisions() {
        divisions = farmService.getFarmDivisions(farm).stream()
                .collect(Collectors.toMap(Division::getUuid, Division::getName));
    }

    private void HashAnimalTypes() {
        animalTypes = animalTypesService.getAll().stream()
                .collect(Collectors.toMap(AnimalType::getUuid, AnimalType::getDescription));
    }

    private void HashRaces() {
        races = raceService.getAll().stream()
                .collect(Collectors.toMap(Race::getUuid, Race::getName));
    }

    public String getBuildingContainingDivision(String divisionId) {
        return farmService.getBuildingContainingDivision(divisionId).getName();
    }

    public Map<String, String> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(Map<String, String> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void clearSelection() {
        animal = new Animal();
    }

    public void save() {

        animal.setFarmId(farm.getUuid());
        updateMotherData();
        try {
            animalService.save(animal);
            Messages.info("Animal Has been saved", "");
        } catch (Exception e) {
            Messages.error("An error has occurred while saving", e.getMessage());
        }

        init();
    }

    public Map<String, String> getRaces() {
        return races;
    }

    public void setRaces(Map<String, String> races) {
        this.races = races;
    }

    public Map<String, String> getDivisions() {
        return divisions;
    }

    public void setDivisions(Map<String, String> divisions) {
        this.divisions = divisions;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public FilterAnimal getFilter() {
        return filter;
    }

    public void setFilter(FilterAnimal filter) {
        this.filter = filter;
    }

    public FilterAnimal[] getFilters(){
        return FilterAnimal.values();
    }

    public enum FilterAnimal {
        All, Current, Discharged;
    }

    public int getTotalAnimals(){
        return animalService.getAll().size();
    }

    public int getTotalAnimalsOfFarm(FilterAnimal filter){
        if (farm == null) return 0;
        switch (filter) {
            case All:
                return animalService.getAnimalsByFarmId(farm.getUuid()).size();
            case Current:
                return animalService.getCurrentAnimalsByFarmId(farm.getUuid()).size();
            case Discharged:
               return animalService.getDischagedAnimalsByFarmId(farm.getUuid()).size();
        }
        return 0;
    }

    public List<Animal> getMothers(){
        return mothers;
    }


    public Animal getMother() {
        return mother;
    }

    public void setMother(Animal mother) {
        this.mother = mother;
        this.setMotherOfficialId("");
    }

    private void updateMotherData(){
        if (mother == null){
            this.animal.setMotherId(null);
            this.animal.setMotherOfficialId(this.getMotherOfficialId());
        }else{
            this.animal.setMotherId(mother.getUuid());
            this.animal.setMotherOfficialId(mother.getOfficialId());
        }
    }

    public String getMotherOfficialId() {
        return motherOfficialId;
    }

    public void setMotherOfficialId(String motherOfficialId) {
        this.motherOfficialId = motherOfficialId;
    }
}
