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

    private List<Animal> animalList;
    private Map<String, String> races = new HashMap<>();
    private Map<String, String> animalTypes = new HashMap<>();
    private Map<String, String> divisions = new HashMap<>();
    private Map<String, String> mothers = new HashMap<>();
    private FilterAnimal filter;

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
        HashMothers();
        HashRaces();
        HashAnimalTypes();
        HashDivisions();
    }

    private void HashMothers() {
        mothers = animalList.stream()
                .filter(p -> p.getSex().equals("Female"))
                .collect(Collectors.toMap(Animal::getUuid, Animal::getOfficialId));
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
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            animalService.save(animal);
            context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Animal saved successfully"));
        } catch (Exception e) {
            context.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error has occurred while saving"));
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

    public Map<String, String> getMothers() {
        return mothers;
    }

    public void setMothers(Map<String, String> mothers) {
        this.mothers = mothers;
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
}
