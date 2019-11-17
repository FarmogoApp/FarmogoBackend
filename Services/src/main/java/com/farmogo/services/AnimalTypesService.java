package com.farmogo.services;

import com.farmogo.dao.AnimalTypeDAO;
import com.farmono.model.AnimalType;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class AnimalTypesService {

    @Inject
    AnimalTypeDAO animalTypeDAO;

    public List<AnimalType> getAll() {
        return animalTypeDAO.getAll();
    }
}
