package com.farmogo.front;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@Named
@SessionScoped
public class AnimalColumnToogler implements Serializable {

    private List<Boolean> visibility;

    @PostConstruct
    public void init() {
        visibility = Arrays.asList(true, false, true, true, true, true, true, false, false);
    }

    public void onColumnsTooggle(ToggleEvent e) {
        visibility.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
    }

    public List<Boolean> getVisibility() {
        return visibility;
    }

    public void setVisibility(List<Boolean> visibility) {
        this.visibility = visibility;
    }
}
