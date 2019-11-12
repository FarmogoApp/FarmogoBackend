package com.farmogo.dao;

import com.farmono.model.Divisions;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DivisionDAO {

    public List<Divisions> getAll() {
        List<Divisions> divisionsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Divisions divisions = new Divisions();

        }
        return divisionsList;
    }
}