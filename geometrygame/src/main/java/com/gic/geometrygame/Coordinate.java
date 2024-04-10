package com.gic.geometrygame;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {

        this.x = x;
        this.y = y;
    }


    public boolean equals(Coordinate other) {
        if (other == null) {
            return false;
        }
        return this.x == other.getX() && this.y == other.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
