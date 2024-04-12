package com.gic.geometrygame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private List<Coordinate> coordinates = new ArrayList<>();
    private boolean finalised;


    public void addCoordinate(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinate value cannot be negative");
        }
        var newCoordinate = new Coordinate(x, y);
        if (isRepeatCoordinate(coordinates, newCoordinate)) {
            throw new IllegalArgumentException(String.format("New coordinates(%d,%d) is invalid!!!", x, y));
        }

        var temp = new ArrayList<>(coordinates);
        temp.add(newCoordinate);
        if (!isConvex(temp)) {
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

    public void createRandomShape() {
        int minCoord = 3;
        int maxCoord = 8;

        var random = new Random();
        var newRandomShape = new ArrayList<Coordinate>();
        int numberOfCoordinates = random.nextInt(maxCoord - minCoord + 1) + minCoord;
        for (int i = 0; i < numberOfCoordinates; i++) {
            var newCoordinate = getNewRandomCoordinate(random, newRandomShape);
            newRandomShape.add(newCoordinate);
        }
        while (!isConvex(newRandomShape)) {
            for (int i = 0; i < numberOfCoordinates; i++) {
                var newCoordinate = getNewRandomCoordinate(random, newRandomShape);
                newRandomShape.set(i, newCoordinate);
            }
        }
        coordinates = newRandomShape;
    }

    public int getCoordinateCount() {
        return coordinates.size();
    }

    public String getShape() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate coord = coordinates.get(i);
            builder.append(String.format("%d:", i + 1))
                    .append(String.format("(%d,%d)", coord.getX(), coord.getY()));
            if (i < coordinates.size() - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private Coordinate getNewRandomCoordinate(Random random, ArrayList<Coordinate> newRandomShape) {
        var newCoordinate = new Coordinate(getCoordinate(random), getCoordinate(random));
        while (isRepeatCoordinate(newRandomShape, newCoordinate)) {
            newCoordinate = new Coordinate(getCoordinate(random), getCoordinate(random));
        }
        return newCoordinate;
    }

    private boolean isRepeatCoordinate(List<Coordinate> existingCoordinates, Coordinate newCoordinate) {
        return existingCoordinates.stream().anyMatch(coord -> coord.equals(newCoordinate));

    }

    private boolean isConvex(List<Coordinate> coordinates) {
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

    private int getCoordinate(Random random) {
        return (int) (random.nextDouble() * 10);
    }


}
