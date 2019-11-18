package com.farmogo.front;


import com.farmogo.model.Farm;
import com.farmogo.services.FarmService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class FarmSwitcherView implements Serializable {

    @Inject
    FarmService farmService;

    private Farm farm;

    private List<Farm> farmList;

    @PostConstruct
    public void init() {
        farm = farmService.getCurrentFarm();
        farmList = farmService.getFarms();
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

    public void changeSessionFarm() {
        farmService.setCurrentFarm(farm);
    }
}
