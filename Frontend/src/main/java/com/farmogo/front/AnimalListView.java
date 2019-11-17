package com.farmogo.front;

import com.farmogo.front.Utils.AnimalUtils;
import com.farmogo.model.Animal;
import com.farmogo.model.AnimalType;
import com.farmogo.model.Farm;
import com.farmogo.model.Race;
import com.farmogo.services.*;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
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
    AnimalService animalService;

    @Inject
    RaceService raceService;

    @Inject
    AnimalTypesService animalTypesService;

    @Inject
    FarmService farmService;

    @Inject
    GlobalSessionService globalSessionService;



    private AnimalUtils animalUtils;

    private Farm farm;

    private List<Animal> animalList;
    private List<Farm> farmList;
    private List<Race> raceList;
    private List<AnimalType> animalTypeList;

    private Animal animal;

    @PostConstruct
    public void init() {
        // TODO: Doesn't update the farm
        // farm = globalSessionService.getFarm();
        // animalList = animalService.getAnimalsByFarmId(farm.getUuid());

        animalList = animalService.getAll();
        raceList = raceService.getAll();
        animalTypeList = animalTypesService.getAll();
        farmList = farmService.getAll();
        animal = new Animal();
        animalUtils = new AnimalUtils(animalList, raceList, animalTypeList, farmList);
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

    public List<Farm> getFarmList() {
        return farmList;
    }

    public void setFarmList(List<Farm> farmList) {
        this.farmList = farmList;
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

    public void delete(){
        animalService.delete(animal);
        init();
    }


    public AnimalUtils getAnimalUtils() {
        return animalUtils;
    }

    public void setAnimalUtils(AnimalUtils animalUtils) {
        this.animalUtils = animalUtils;
    }
}
