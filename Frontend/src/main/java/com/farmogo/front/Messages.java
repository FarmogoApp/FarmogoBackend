package com.farmogo.front;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {

    public static void info(String summary, String detail){
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));

    }
    public static void warn(String summary, String detail){
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }
    public static void error(String summary, String detail){
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        currentInstance.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }
}
