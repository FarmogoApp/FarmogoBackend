package com.farmogo.front.Utils;

import com.farmogo.model.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Animal Utils class used to minimize DB access
 */
public class AnimalUtils {

    private List<Animal> animalList;
    private List<Race> raceList;
    private List<AnimalType> animalTypeList;
    private List<Division> divisionList;

    public AnimalUtils(){}

    public AnimalUtils(List<Animal> animalList, List<Race> raceList, List<AnimalType> animalTypeList, List<Division> divisionList) {
        this.animalList = animalList;
        this.raceList = raceList;
        this.animalTypeList = animalTypeList;
        this.divisionList = divisionList;
    }

    public String divisionIdToName(String divisionId){
        Optional<Division> division = divisionList.stream()
                .filter(p -> p.getUuid().equals(divisionId))
                .findFirst();

        return division.isPresent() ? division.get().getName() : "";
    }

    public List<Animal> getMothersList(){
        return animalList.stream()
                .filter(p -> p.getSex().equals("Female"))
                .collect(Collectors.toList());
    }

    public String motherIdToOfficialId(String motherId){
        Optional<Animal> animalMother = animalList.stream()
                .filter(p -> p.getUuid().equals(motherId))
                .findFirst();

        return animalMother.isPresent() ? animalMother.get().getOfficialId() : motherId;
    }

    public String raceIdToName(String raceId){
        Optional<Race> race = raceList.stream()
                .filter(p -> p.getUuid().equals(raceId))
                .findFirst();

        return race.isPresent() ? race.get().getName() : "";
    }

    public String animalTypeToDescription(String animalType){
        Optional<AnimalType> at = animalTypeList.stream()
                .filter(p -> p.getAnimalType().equals(animalType))
                .findFirst();

        return at.isPresent() ? at.get().getDescription() : "";
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public void setRaceList(List<Race> raceList) {
        this.raceList = raceList;
    }

    public void setAnimalTypeList(List<AnimalType> animalTypeList) {
        this.animalTypeList = animalTypeList;
    }


}
