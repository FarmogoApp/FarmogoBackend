package com.farmogo.front;


import com.farmogo.front.Utils.AnimalUtils;
import com.farmogo.model.Animal;
import com.farmogo.model.AnimalType;
import com.farmogo.model.Farm;
import com.farmogo.model.Race;
import com.farmogo.services.AnimalService;
import com.farmogo.services.AnimalTypesService;
import com.farmogo.services.FarmService;
import com.farmogo.services.RaceService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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
    private List<Farm> farmList;
    private List<Race> raceList;
    private List<AnimalType> animalTypeList;

    private Animal animal;
    private AnimalUtils animalUtils;


    @PostConstruct
    public void init() {

        animalList = animalService.getAll();
        raceList = raceService.getAll();
        animalTypeList = animalTypesService.getAll();
        farmList = farmService.getAll();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();

        if (params.containsKey("animal")){
            String animalId = params.get("animal");
            animal = animalService.getAnimalById(animalId);
        } else {
            animal = new Animal();
        }

        animalUtils = new AnimalUtils(animalList, raceList, animalTypeList, farmList);
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

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}
