package com.farmogo.dao;

import com.farmogo.model.Incidence;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class IncidencesDAO {

    public List<Incidence> getAll() {
        List<Incidence> incidencesList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Incidence incidences = new Incidence();

        }
        return incidencesList;
    }
}