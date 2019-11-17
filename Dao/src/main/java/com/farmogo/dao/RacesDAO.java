package com.farmogo.dao;

import com.farmogo.model.Races;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RacesDAO {

    public List<Races> getAll() {
        List<Races> racesList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Races races = new Races();

        }
        return racesList;
    }
}