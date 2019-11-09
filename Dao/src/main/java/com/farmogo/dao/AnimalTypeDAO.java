package com.farmogo.dao;

import com.farmono.model.AnimalType;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class AnimalTypeDAO {

    private List<AnimalType> animalTypeList;

    @PostConstruct
    public void init() {
        animalTypeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AnimalType animalType = new AnimalType();
            animalType.setAnimalType(i + 1);
            animalType.setDescription("ANIMAL TYPE: " + i + 1);
            animalType.setIcon("ICON + " + i + 1);
            animalTypeList.add(animalType);
        }
    }


    public List<AnimalType> getAll() {
        return animalTypeList;
    }

    public void save(AnimalType animalType) {
        // try update
        for (int i = 0; i < animalTypeList.size(); i++) {
            if (animalTypeList.get(i).getAnimalType() == animalType.getAnimalType()) {
                animalTypeList.set(i, animalType);
                return;
            }
        }

        // new
        int max = animalTypeList.stream().mapToInt(AnimalType::getAnimalType).max().orElse(0);
        animalType.setAnimalType(max + 1);
        animalTypeList.add(animalType);
    }


}
