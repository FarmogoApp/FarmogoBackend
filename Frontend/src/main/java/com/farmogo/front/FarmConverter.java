package com.farmogo.front;

import com.farmogo.model.AccessNotAllowed;
import com.farmogo.model.Farm;
import com.farmogo.services.FarmService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FarmConverter implements Converter<Farm> {

    @Inject
    FarmService farmService;

    @Override
    public Farm getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try {
            return farmService.get(s);
        } catch (AccessNotAllowed accessNotAllowed) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Farm farm) {
        if (farm == null) return null;
        return farm.getUuid();
    }
}
