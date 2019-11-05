package com.farmogo.dao;

import com.farmono.model.AnimalType;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class AnimalTypeDAO {


    public List<AnimalType> getAll() {
        List<AnimalType> animalTypeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AnimalType animalType = new AnimalType();
            animalType.setAnimalType(i);
            animalType.setDescription("ANIMAL TYPE: " + i);
            animalType.setIcon("ICON + " + i);
            animalTypeList.add(animalType);
        }
        return animalTypeList;
    }
}
