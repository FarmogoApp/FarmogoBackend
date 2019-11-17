package com.farmogo.dao.mock;

import com.farmogo.dao.FarmDao;
import com.farmogo.model.Farm;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Typed;
import java.util.ArrayList;
import java.util.List;

@Typed({})
public class FarmMockDao implements FarmDao {

    private List<Farm> farmList;

    @PostConstruct
    public void init() {
        farmList = new ArrayList<>();
        for (int i = 1_000_000; i < 1_000_008; i++) {
            Farm f = new Farm();
            f.setUuid("id" + i);
            f.setName("Farm " + i);
            f.setOfficialId("xx" + i);
            farmList.add(f);
        }
    }

    @Override
    public List<Farm> getFarmByUser(String userId) {
        return farmList;
    }

    @Override
    public List<Farm> getAll() {
        return null;
    }

    @Override
    public Farm get(String id) {
        return farmList.stream().filter(f -> f.getUuid().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(Farm farm) {

    }
}
