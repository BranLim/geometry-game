package com.gic.geometrygame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game newGame;

    @BeforeEach
    void setUp() {
        newGame = new Game();
    }


    @Test
    void shouldReturnIncompleteShapeWhenOnlyTwoCoordinatesAdded() {
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);

        assertTrue(newGame.getCoordinate(1).equals(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equals(new Coordinate(5, 1)));
        assertFalse(newGame.isShapeComplete());
    }

    @Test
    void shouldThrowErrorWhenCoordinateIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> newGame.addCoordinate(-1, 1));
    }

    @Test
    void shouldThrowErrorWhenCoordinateGivenRepeats() {
        newGame.addCoordinate(4, 2);
        newGame.addCoordinate(2, 2);

        assertThrows(IllegalArgumentException.class, () -> newGame.addCoordinate(2, 2));
    }

    @Test
    void shouldReturnCompleteShapeWhenGiven3ValidCoordinates() {
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);
        newGame.addCoordinate(5, 5);

        assertTrue(newGame.getCoordinate(1).equals(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equals(new Coordinate(5, 1)));
        assertTrue(newGame.getCoordinate(3).equals(new Coordinate(5, 5)));
        assertTrue(newGame.isShapeComplete());
    }

    @Test
    void shouldThrowAnErrorWhenGiven4CoordinatesThatGivesConcaveShape() {
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(3, 1);
        newGame.addCoordinate(5, 5);

        assertTrue(newGame.getCoordinate(1).equals(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equals(new Coordinate(3, 1)));
        assertTrue(newGame.getCoordinate(3).equals(new Coordinate(5, 5)));
        assertThrows(IllegalArgumentException.class, () -> newGame.addCoordinate(4, 0));
    }

    @Test
    void shouldReturnCompleteShapeWhenAddedAtLeastThreeValidCoordinatesAndGameIsFinalised() {
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);
        newGame.addCoordinate(5, 5);
        newGame.finaliseShape();

        assertTrue(newGame.getCoordinate(1).equals(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equals(new Coordinate(5, 1)));
        assertTrue(newGame.getCoordinate(3).equals(new Coordinate(5, 5)));
        assertTrue(newGame.isShapeComplete());
        assertTrue(newGame.isShapeFinalised());
    }

    @Test
    void shouldReturnFalseWhenGivenCoordinateIsNotWithinFinalisedShape() {
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);
        newGame.addCoordinate(5, 5);
        newGame.finaliseShape();

        boolean checkIShape = newGame.checkCoordinateInShape(0, 2);

        assertTrue(newGame.getCoordinate(1).equals(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equals(new Coordinate(5, 1)));
        assertTrue(newGame.getCoordinate(3).equals(new Coordinate(5, 5)));
        assertTrue(newGame.isShapeFinalised());
        assertFalse(checkIShape);
    }

    @Test
    void shouldReturnTrueWhenGivenCoordinateIsWithinFinalisedShape() {
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);
        newGame.addCoordinate(5, 5);
        newGame.finaliseShape();

        boolean checkIShape = newGame.checkCoordinateInShape(2, 2);

        assertTrue(newGame.getCoordinate(1).equals(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equals(new Coordinate(5, 1)));
        assertTrue(newGame.getCoordinate(3).equals(new Coordinate(5, 5)));
        assertTrue(newGame.isShapeFinalised());
        assertTrue(checkIShape);
    }
}