package com.gic.geometrygame;

import java.util.ArrayList;
import java.util.List;

public class Shape {
    private List<Coordinate> coordinates = new ArrayList<Coordinate>();

    public Shape() {
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public boolean addPoint(Coordinate newCoordinate) {
        return getCoordinates().add(newCoordinate);
    }

    public Coordinate getPointAt(int coordinateNumber) {
        return getCoordinates().get(coordinateNumber - 1);
    }
}