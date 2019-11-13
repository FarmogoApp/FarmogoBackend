package com.farmogo.dao;

import com.farmogo.model.IncidenceType;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class IncidenceTypeDAO {

    public List<IncidenceType> getAll() {
        List<IncidenceType> incidenceTypeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            IncidenceType incidenceType = new IncidenceType();

        }
        return incidenceTypeList;
    }
}