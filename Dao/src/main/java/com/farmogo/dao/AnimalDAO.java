package com.farmogo.dao;

import com.farmogo.model.Animal;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AnimalDAO {

    public List<Animal> getAll() {
        List<Animal> animalList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Animal animal = new Animal();

        }
        return animalList;
    }
}

