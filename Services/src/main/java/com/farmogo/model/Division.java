package com.farmogo.model;



import java.io.Serializable;

public class Division implements Serializable {
    private String uuid;
    private String name;

    public Division(){

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
