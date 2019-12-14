package com.farmogo.front;

import com.farmogo.model.Race;
import com.farmogo.services.HasRelationatedDataException;
import com.farmogo.services.NotificationService;
import com.farmogo.services.RaceService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import static com.farmogo.services.NotificationService.ALL_TOPIC;

@Named
//@RequestScoped
@ViewScoped
public class RaceView implements Serializable {

    @Inject
    RaceService raceService;

    @Inject
    NotificationService notificationService;

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

        try {
            notificationService.sendNotificationToTopic("Sync pending", "The animal races have been updated", ALL_TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Messages.info("Race " + race.getName()+ " has been saved","");
        init();
    }

    public void delete() {
        try {
            raceService.delete(race);
            Messages.info("Race " + race.getName()+ " has been deleted","");
        }catch (HasRelationatedDataException ex){
            Messages.error("Race is assigned to animal","If race is assigned tho animal you can't delete this");
        }
        init();
    }

}
