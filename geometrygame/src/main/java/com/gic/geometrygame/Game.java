package com.gic.geometrygame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private Shape shape = new Shape();
    private boolean finalised;


    public void addCoordinate(int x, int y) {
        checkCoordinateIsPositive(x, y);
        checkRepeatCoordinate(shape.getCoordinates(), x, y);
        var newCoordinate = new Coordinate(x, y);
        var temp = new ArrayList<>(shape.getCoordinates());
        temp.add(newCoordinate);
        if (!isConvex(temp)) {
            throw new IllegalArgumentException(String.format("New coordinates(%d,%d) is invalid!!!", x, y));
        }
        shape.addPoint(newCoordinate);
    }

    private void checkCoordinateIsPositive(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinate value cannot be negative");
        }
    }

    public Coordinate getCoordinate(int coordinateNumber) {
        return shape.getPointAt(coordinateNumber);
    }

    public boolean isShapeFinalised() {
        return this.finalised;
    }

    public void finaliseShape() {
        if (shape.getCoordinates().size() < 3) {
            throw new IllegalStateException("Shape is incomplete");
        }
        this.finalised = true;
    }

    public boolean isShapeComplete() {
        return shape.getCoordinates().size() >= 3;
    }

    public boolean checkCoordinateInShape(int x, int y) {
        boolean coordinateInShape = false;
        int c = shape.getCoordinates().size();
        int j = c - 1;
        for (int i = 0; i < c; i++) {
            Coordinate coordinate1 = shape.getCoordinates().get(i);
            Coordinate coordinate2 = shape.getCoordinates().get(j);
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
        shape.setCoordinates(newRandomShape);
    }

    public int getCoordinateCount() {
        return shape.getCoordinates().size();
    }

    public String getShape() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < shape.getCoordinates().size(); i++) {
            Coordinate coord = shape.getCoordinates().get(i);
            builder.append(String.format("%d:", i + 1))
                    .append(String.format("(%d,%d)", coord.getX(), coord.getY()));
            if (i < shape.getCoordinates().size() - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private Coordinate getNewRandomCoordinate(Random random, ArrayList<Coordinate> newRandomShape) {
        int x = getCoordinate(random);
        int y = getCoordinate(random);
        while (true) {
            try {
                checkRepeatCoordinate(newRandomShape, x, y);
                break;
            } catch (IllegalArgumentException e) {
                x = getCoordinate(random);
                y = getCoordinate(random);
            }
        }
        return new Coordinate(x, y);
    }

    private void checkRepeatCoordinate(List<Coordinate> existingCoordinates, int x, int y) {
        if (existingCoordinates.stream().anyMatch(coord -> coord.equals(new Coordinate(x, y)))) {
            throw new IllegalArgumentException(String.format("New coordinates(%d,%d) is invalid!!!", x, y));
        }
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
