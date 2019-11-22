package com.farmogo.front;


import com.farmogo.front.Utils.AnimalUtils;
import com.farmogo.model.*;
import com.farmogo.services.AnimalService;
import com.farmogo.services.AnimalTypesService;
import com.farmogo.services.FarmService;
import com.farmogo.services.RaceService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
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

    private String race;
    private String animalType;
    private String division;
    private String motherId;

    private Farm farm;
    private Animal animal;
    private ArrayList<Animal> animalData;

    @PostConstruct
    public void init() {
        farm = farmService.getCurrentFarm();

        if(farm != null) {

            FacesContext facesContext = FacesContext.getCurrentInstance();
            Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();

            if (params.containsKey("animalId")){
                animal = animalService.getAnimalById(params.get("animalId"));
                race = raceService.get(animal.getRaceId()).getName();
                animalType = animalTypesService.get(animal.getAnimalTypeId()).getDescription();
                motherId = animalService.getAnimalById(animal.getMotherId()).getOfficialId();
                division = farmService.getDivisionById(animal.getDivisionId()).getName();

            } else {
                animal = new Animal();
            }

            // Exporter needs a list...
            animalData = new ArrayList<>();
            animalData.add(animal);

        }
    }


    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
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
