package com.farmogo.services;

import com.farmogo.dao.AnimalDAO;
import com.farmono.model.Animal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;


@Stateless
public class AnimalService {

    @Inject
    AnimalDAO animalDAO;

    public List<Animal> getAll() {
        return animalDAO.getAll();
    }
}


