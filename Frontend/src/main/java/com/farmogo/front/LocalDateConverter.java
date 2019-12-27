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
import java.time.format.DateTimeFormatter;
import java.util.PropertyResourceBundle;


@Named
@ApplicationScoped
@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component, context));
        try {
            return LocalDate.parse(value, formatter);
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
        return formatter.format((LocalDate) value);
    }

    private String extractPattern(UIComponent component, FacesContext context) {
        // try to get infos from calendar component
        if (component instanceof Calendar) {
            Calendar calendarComponent = (Calendar) component;
            return calendarComponent.getPattern();
        }

        if (component instanceof DatePicker) {
            DatePicker datePicker = (DatePicker) component;
            return datePicker.getPattern();
        }

        if (component instanceof org.primefaces.component.inputtext.InputText ||
                component instanceof javax.faces.component.html.HtmlOutputText) {
            PropertyResourceBundle i18n = context.getApplication().evaluateExpressionGet(context, "#{i18n}", PropertyResourceBundle.class);
            return i18n.getString("date.format");
        }
        return null;
    }
}