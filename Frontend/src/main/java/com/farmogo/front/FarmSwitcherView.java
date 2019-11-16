package com.farmogo.front;


import com.farmogo.model.Farm;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class FarmSwitcherView implements Serializable {

    @Inject
    GlobalSession globalSession;



    private Farm farm;

    private List<Farm> farmList;

    @PostConstruct
    public void init() {
        farm = globalSession.getFarm();


        // TODO: Pendint to get from Service layer
        farmList = new ArrayList<>();
        for (int i = 1_000_000; i < 1_000_008; i++) {
            Farm f = new Farm();
            f.setName("Farm " + i);
            f.setOfficialId( "xx" + i);
            farmList.add(f);
        }
    }

    public void changeFarm(){
        globalSession.setFarm(farm);
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public List<Farm> getFarmList() {
        return farmList;
    }

    public void setFarmList(List<Farm> farmList) {
        this.farmList = farmList;
    }
}
