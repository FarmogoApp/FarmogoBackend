package com.farmogo.front;

import com.farmogo.model.Animal;
import com.farmogo.model.Race;
import com.farmogo.services.AnimalService;
import com.farmogo.services.RaceService;
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

    private List<Animal> animalList;
    private List<Race> raceList;

    private Animal animal;

    @PostConstruct
    public void init() {
        animalList = animalService.getAll();
        raceList = raceService.getAll();
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

    public String motherIdToOfficialId(String motherId){
        Optional<Animal> animalMother = animalList.stream()
                .filter(p -> p.getUuid().equals(motherId))
                .findFirst();

        return animalMother.isPresent() ? animalMother.get().getOfficialId() : "";
    }

    public String raceIdToName(String raceId){
        Optional<Race> race = raceList.stream()
                .filter(p -> p.getUuid().equals(raceId))
                .findFirst();

        return race.isPresent() ? race.get().getName() : "";
    }

    public List<Animal> getMothersList(){
        return animalList.stream()
                .filter(p -> p.getSex().equals("Female"))
                .collect(Collectors.toList());
    }

}
