package com.gic.geometrygame;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Coordinate> coordinates = new ArrayList<>();


    public void addCoordinate(int x, int y) {
        if (x < 0 || y < 0) {
            throw new InvalidArgumentException("Coordinate value cannot be negative");
        }
        coordinates.add(new Coordinate(x, y));
    }

    public Coordinate getCoordinate(int coordinateNumber) {

        return coordinates.get(coordinateNumber - 1);
    }
}
