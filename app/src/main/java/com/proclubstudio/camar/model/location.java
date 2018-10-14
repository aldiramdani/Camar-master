package com.proclubstudio.camar.model;

import java.io.Serializable;
import java.util.List;

public class location implements Serializable{
    private List<Double> coordinates = null;

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
