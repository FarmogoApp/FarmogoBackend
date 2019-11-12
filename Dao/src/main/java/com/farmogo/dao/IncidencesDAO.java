package com.farmogo.dao;

import com.farmono.model.Incidences;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class IncidencesDAO {

    public List<Incidences> getAll() {
        List<Incidences> incidencesList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Incidences incidences = new Incidences();

        }
        return incidencesList;
    }
}