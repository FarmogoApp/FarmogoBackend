package com.farmono.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String uuid;
    private String email;
    private String name;
    private String password;
    private String billData;
    private List<Incidences> incidencesList;

    public User(){

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBillData() {
        return billData;
    }

    public void setBillData(String billData) {
        this.billData = billData;
    }
    public List<Incidences> getIncidencesList() {
        return incidencesList;
    }

    public void setIncidencesList(List<Incidences> incidencesList) {
        this.incidencesList = incidencesList;
    }
}
