package com.proclubstudio.camar.model;

import java.io.Serializable;

public class JSON implements Serializable{
    private String tittle;
    private String url;
    private com.proclubstudio.camar.model.location location;
    private double mag;
    private double depth;
    private String place;
    private Long time;
    private Integer tsunami;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public com.proclubstudio.camar.model.location getLocation() { return location; }

    public void setLocation(com.proclubstudio.camar.model.location location) { this.location = location; }
    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getTsunami() {
        return tsunami;
    }

    public void setTsunami(Integer tsunami) {
        this.tsunami = tsunami;
    }
}
