package com.farmogo.dao;

import com.farmogo.model.GrantAcces;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class GrantAccesDAO {

    public List<GrantAcces> getAll() {
        List<GrantAcces> grantAccesList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GrantAcces grantAcces = new GrantAcces();

        }
        return grantAccesList;
    }
}