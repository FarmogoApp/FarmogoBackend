package com.farmogo.front;

import com.farmogo.front.Utils.AnimalUtils;
import com.farmogo.model.*;
import com.farmogo.services.*;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
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


    private AnimalUtils animalUtils;
    private Farm farm;
    private Animal animal;

    private List<Animal> animalList;
    private List<Race> raceList;
    private List<AnimalType> animalTypeList;
    private List<Division> divisionList;


    @PostConstruct
    public void init() {
        farm = farmService.getCurrentFarm();

        if(farm != null) {
            animalList = animalService.getAnimalsByFarmId(farm.getUuid());
            divisionList = farmService.getFarmDivisions(farm);
            raceList = raceService.getAll();
            animalTypeList = animalTypesService.getAll();
            animalUtils = new AnimalUtils(animalList, raceList, animalTypeList, divisionList);
        }

        animal = new Animal();
    }


    public List<Animal> getAnimalList() {
        return animalList;
    }

    public List<Race> getRaceList() {
        return raceList;
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

    public List<AnimalType> getAnimalTypeList() {
        return animalTypeList;
    }

    public void setAnimalTypeList(List<AnimalType> animalTypeList) {
        this.animalTypeList = animalTypeList;
    }

    public void onRowEdit(RowEditEvent event) {
        Animal animalEvent = (Animal) event.getObject();
        animalEvent.setFarmId(farm.getUuid());
        animalService.save(animalEvent);
        init();
    }

    public void clearSelection(){
        animal = new Animal();
    }

    public void save(){

        animal.setFarmId(farm.getUuid());
        animalService.save(animal);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Animal saved successfully"));
        init();
    }


    public AnimalUtils getAnimalUtils() {
        return animalUtils;
    }

    public void setAnimalUtils(AnimalUtils animalUtils) {
        this.animalUtils = animalUtils;
    }

    public List<Division> getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(List<Division> divisionList) {
        this.divisionList = divisionList;
    }
}
