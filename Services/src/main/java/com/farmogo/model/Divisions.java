package com.farmogo.model;



import java.io.Serializable;

public class Divisions implements Serializable {
    private String uuid;
    private String idBuild;
    private String name;
  //  private JSObject coordGpsTopLeft;
   // private JSObject coordGpsBottomRight;


    private Building building;
    public Divisions(){

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIdBuild() {
        return idBuild;
    }

    public void setIdBuild(String idBuild) {
        this.idBuild = idBuild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

}
