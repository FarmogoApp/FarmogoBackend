package com.farmogo.front;

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
        return farmService.get(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Farm farm) {
        return farm.getUuid();
    }
}
