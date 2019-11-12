package com.farmogo.dao;

import com.farmono.model.IncidenceTypes;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class IncidenceTypeDAO {

    public List<IncidenceTypes> getAll() {
        List<IncidenceTypes> incidenceTypesList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            IncidenceTypes incidenceTypes = new IncidenceTypes();

        }
        return incidenceTypesList;
    }
}