package com.farmogo.front;


import com.farmogo.front.Utils.AnimalUtils;
import com.farmogo.model.Animal;
import com.farmogo.model.AnimalType;
import com.farmogo.model.Farm;
import com.farmogo.model.Race;
import com.farmogo.services.*;
import org.primefaces.event.RowEditEvent;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class AnimalDataView implements Serializable {

    @Inject
    AnimalService animalService;

    @Inject
    RaceService raceService;

    @Inject
    AnimalTypesService animalTypesService;

    @Inject
    FarmService farmService;


    private List<Animal> animalList;
    private List<Race> raceList;
    private List<AnimalType> animalTypeList;

    private Animal animal;
    private AnimalUtils animalUtils;
    private Farm farm;

    private ArrayList<Animal> animalData;


    @PostConstruct
    public void init() {
        farm = farmService.getCurrentFarm();
        if(farm != null) animalList = animalService.getAnimalsByFarmId(farm.getUuid());

        raceList = raceService.getAll();
        animalTypeList = animalTypesService.getAll();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
        if (params.containsKey("animalId")){
            animal = animalService.getAnimalById(params.get("animalId"));
        } else {
            animal = new Animal();
        }

        // Exporter needs a list...
        animalData = new ArrayList<>();
        animalData.add(animal);

        animalUtils = new AnimalUtils(animalList, raceList, animalTypeList);
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

    public List<Race> getRaceList() {
        return raceList;
    }

    public void setRaceList(List<Race> raceList) {
        this.raceList = raceList;
    }

    public void onRowEdit(RowEditEvent event) {
        animalService.save((Animal) event.getObject());
        init();
    }

    public void clearSelection(){
        animal = new Animal();
    }

    public void save(){
        animalService.save(animal);
        init();
    }

    public AnimalUtils getAnimalUtils() {
        return animalUtils;
    }

    public void setAnimalUtils(AnimalUtils animalUtils) {
        this.animalUtils = animalUtils;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public ArrayList<Animal> getAnimalData() {
        return animalData;
    }

    public void setAnimalData(ArrayList<Animal> animalData) {
        this.animalData = animalData;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
