package com.farmono.model;

import netscape.javascript.JSObject;

import java.io.Serializable;

public class Divisions implements Serializable {
    private String uuid;
    private String idBuild;
    private String name;
    private JSObject coordGpsTopLeft;
    private JSObject coordGpsBottomRight;

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

    public JSObject getCoordGpsTopLeft() {
        return coordGpsTopLeft;
    }

    public void setCoordGpsTopLeft(JSObject coordGpsTopLeft) {
        this.coordGpsTopLeft = coordGpsTopLeft;
    }

    public JSObject getCoordGpsBottomRight() {
        return coordGpsBottomRight;
    }

    public void setCoordGpsBottomRight(JSObject coordGpsBottomRight) {
        this.coordGpsBottomRight = coordGpsBottomRight;
    }
}
