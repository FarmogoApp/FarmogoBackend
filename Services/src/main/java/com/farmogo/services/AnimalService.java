package com.farmogo.services;

import com.farmogo.model.Animal;

import javax.ejb.Stateless;
import java.util.List;


@Stateless
public class AnimalService {
    /*
        @Inject
        AnimalDAO animalDAO;
    */
    public List<Animal> getAll() {
        return null;
        //return animalDAO.getAll();
    }


}


