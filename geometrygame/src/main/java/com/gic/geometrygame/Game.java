package com.gic.geometrygame;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Coordinate> coordinates = new ArrayList<>();


    public void addCoordinate(int x, int y) {
        if (x < 0 || y < 0) {
            throw new InvalidArgumentException("Coordinate value cannot be negative");
        }
        Coordinate newCoordinate = new Coordinate(x, y);
        if (coordinates.stream().anyMatch(coord -> coord.equals(newCoordinate))){
            throw new InvalidArgumentException(String.format("New coordinates(%d,%d) is invalid!!!", x, y));
        }
        coordinates.add(newCoordinate);
    }

    public Coordinate getCoordinate(int coordinateNumber) {

        return coordinates.get(coordinateNumber - 1);
    }

    public boolean isShapeCompleted() {
        return false;
    }

}
