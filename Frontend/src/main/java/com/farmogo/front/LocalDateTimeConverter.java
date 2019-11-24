package com.farmogo.front;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.datepicker.DatePicker;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Named
@ApplicationScoped
@FacesConverter(forClass = LocalDateTime.class)
public class LocalDateTimeConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component, context));
        try {
            return LocalDateTime.parse(value, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || (value instanceof String && StringUtils.isBlank((String) value))) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component, context));
        return formatter.format((LocalDateTime) value);
    }

    private String extractPattern(UIComponent component, FacesContext context) {
        // try to get infos from calendar component
        if (component instanceof javax.faces.component.html.HtmlOutputText) {
            return "dd/MM/yyyy HH:mm:ss";
        }
        if (component instanceof org.primefaces.component.inputtext.InputText) {
            return "dd/MM/yyyy HH:mm:ss";
        }
        return null;
    }
}