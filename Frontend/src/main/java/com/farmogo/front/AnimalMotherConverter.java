package com.farmogo.front;

import com.farmogo.model.AccessNotAllowed;
import com.farmogo.model.Animal;
import com.farmogo.services.AnimalService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AnimalMotherConverter implements Converter<Animal> {

    @Inject
    AnimalService animalService;

    @Override
    public Animal getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty() || "External Animal".equals(s)) return null;
        try {
            return animalService.get(s);
        } catch (AccessNotAllowed accessNotAllowed) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Animal animal) {
        if (animal == null) return null;
        return animal.getUuid();
    }
}
