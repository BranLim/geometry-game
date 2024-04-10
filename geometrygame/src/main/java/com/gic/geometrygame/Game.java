package com.gic.geometrygame;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Coordinate> coordinates = new ArrayList<>();
    private boolean finalised;


    public void addCoordinate(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinate value cannot be negative");
        }
        Coordinate newCoordinate = new Coordinate(x, y);
        if (coordinates.stream().anyMatch(coord -> coord.equals(newCoordinate))) {
            throw new IllegalArgumentException(String.format("New coordinates(%d,%d) is invalid!!!", x, y));
        }
        List temp = new ArrayList<Coordinate>(coordinates);
        temp.add(newCoordinate);
        if (!IsConvex(temp)) {
            throw new IllegalArgumentException(String.format("New coordinates(%d,%d) is invalid!!!", x, y));
        }
        coordinates.add(newCoordinate);
    }


    public Coordinate getCoordinate(int coordinateNumber) {
        return coordinates.get(coordinateNumber - 1);
    }

    public boolean isShapeFinalised() {
        return this.finalised;
    }

    public void finaliseShape() {
        if (coordinates.size() < 3) {
            throw new IllegalStateException("Shape is incomplete");
        }
        this.finalised = true;
    }

    public boolean isShapeComplete() {
        return coordinates.size() >= 3;
    }

    public boolean checkCoordinateInShape(int x, int y) {
        boolean coordinateInShape = false;
        int c = coordinates.size();
        int j = c - 1;
        for (int i = 0; i < c; i++) {
            Coordinate coordinate1 = coordinates.get(i);
            Coordinate coordinate2 = coordinates.get(j);
            if ((coordinate1.getY() < y && coordinate2.getY() >= y
                    || coordinate2.getY() < y && coordinate1.getY() >= y)
                    && (coordinate1.getX() <= x || coordinate2.getX() <= x)) {
                if (coordinate1.getX() + (y - coordinate1.getY()) / (coordinate2.getY() - coordinate1.getY()) * (coordinate2.getX() - coordinate1.getX()) < x) {
                    coordinateInShape = !coordinateInShape;
                }
            }
            j = i;
        }
        return coordinateInShape;
    }

    private boolean IsConvex(List<Coordinate> coordinates) {
        int n = coordinates.size();
        boolean isConvex = true;
        for (int i = 0; i < n; i++) {
            int dx1 = coordinates.get((i + 2) % n).getX() - coordinates.get((i + 1) % n).getX();
            int dy1 = coordinates.get((i + 2) % n).getY() - coordinates.get((i + 1) % n).getY();
            int dx2 = coordinates.get(i).getX() - coordinates.get((i + 1) % n).getX();
            int dy2 = coordinates.get(i).getY() - coordinates.get((i + 1) % n).getY();
            int crossProduct = dx1 * dy2 - dy1 * dx2;
            if (i == 0) {
                isConvex = crossProduct > 0;
            } else if ((crossProduct > 0 && !isConvex) || (crossProduct < 0 && isConvex)) {
                return false;
            }
        }
        return true;
    }
}
