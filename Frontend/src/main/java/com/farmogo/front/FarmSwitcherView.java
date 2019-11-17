package com.farmogo.front;


import com.farmogo.model.Farm;
import com.farmogo.services.FarmService;
import com.farmogo.services.GlobalSessionService;

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

    @Inject
    GlobalSessionService globalSessionService;

    private Farm farm;

    private List<Farm> farmList;

    @PostConstruct
    public void init() {
        farm = globalSessionService.getFarm();
        farmList = farmService.getFarms(globalSessionService.getUser());
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

    public void changeSessionFarm(){
        globalSessionService.setFarm(farm);
    }
}
