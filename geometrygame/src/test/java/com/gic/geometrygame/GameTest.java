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
    void shouldBeSuccessfulWhenGiven3ValidCoordinates() {
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);
        newGame.addCoordinate(5, 5);

        assertTrue(newGame.getCoordinate(1).equals(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equals(new Coordinate(5, 1)));
        assertTrue(newGame.getCoordinate(3).equals(new Coordinate(5, 5)));
    }

    @Test
    void shouldReturnIncompleteShapeWhenOnlyTwoCoordinatesAdded() {
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);

        assertTrue(newGame.getCoordinate(1).equals(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equals(new Coordinate(5, 1)));
        assertFalse(newGame.isShapeFinalised());
    }

    @Test
    void shouldThrowErrorWhenCoordinateIsNegative() {
        assertThrows(InvalidArgumentException.class, () -> newGame.addCoordinate(-1, 1));
    }

    @Test
    void shouldThrowErrorWhenCoordinateGivenRepeats() {
        newGame.addCoordinate(4,2);
        newGame.addCoordinate(2,2);

        assertThrows(InvalidArgumentException.class, ()->newGame.addCoordinate(2,2));
    }

    @Test
    void shouldThrowErrorWhenCoordinatesDoNotConnect() {
        newGame.addCoordinate(4,2);
        newGame.addCoordinate(2,2);

        assertThrows(InvalidArgumentException.class, ()->newGame.addCoordinate(5,3));
    }

    @Test
    void shouldReturnCompleteShapeWhenAddedAtLeastThreeCoordinatesAndGameIsFinalised() {
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);
        newGame.addCoordinate(5, 5);
        newGame.finaliseShape();

        assertTrue(newGame.getCoordinate(1).equals(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equals(new Coordinate(5, 1)));
        assertTrue(newGame.getCoordinate(3).equals(new Coordinate(5, 5)));
        assertTrue(newGame.isShapeFinalised());
    }

}