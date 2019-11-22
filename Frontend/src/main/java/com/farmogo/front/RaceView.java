package com.farmogo.front;

import com.farmogo.model.AnimalType;
import com.farmogo.model.Race;
import com.farmogo.services.RaceService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
//@RequestScoped
@ViewScoped
public class RaceView implements Serializable {

    @Inject
    RaceService raceService;
    private List<Race> raceList;
    private Race race;

    @PostConstruct
    public void init() {
        raceList = raceService.getAll();
        race = new Race();
    }

    public List<Race> getRaceList() {
        return raceList;
    }

    public void setRaceList(List<Race> raceList) {
        this.raceList = raceList;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void onRowEdit(RowEditEvent event) {
        raceService.save((Race) event.getObject());
        init();
    }


    public void clearSelection(){
        race = new Race();
    }

    public void save(){
        raceService.save(race);
        init();
    }

    public void delete(){

        raceService.delete(race);
        init();
    }

}
