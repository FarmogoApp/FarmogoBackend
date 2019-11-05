package com.farmogo.services;

import com.farmogo.dao.AnimalTypeDAO;
import com.farmono.model.AnimalType;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;


@Dependent
public class AnimalTypesService {

    @Inject
    AnimalTypeDAO animalTypeDAO;

    public List<AnimalType> getAll() {
        return animalTypeDAO.getAll();
    }
}
