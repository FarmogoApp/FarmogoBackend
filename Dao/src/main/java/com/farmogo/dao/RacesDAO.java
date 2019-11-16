package com.farmogo.dao;

import com.farmogo.model.Race;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RacesDAO {

    public List<Race> getAll() {
        List<Race> raceList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Race race = new Race();

        }
        return raceList;
    }
}