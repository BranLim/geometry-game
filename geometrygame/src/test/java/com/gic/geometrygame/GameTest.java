package com.gic.geometrygame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    @Test
    void shouldBeSuccessfulWhenGiven3ValidCoordinates() {
        Game newGame = new Game();
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);
        newGame.addCoordinate(5, 5);

        assertTrue(newGame.getCoordinate(1).equal(new Coordinate(1, 1)));
        assertTrue(newGame.getCoordinate(2).equal(new Coordinate(5, 1)));
        assertTrue(newGame.getCoordinate(3).equal(new Coordinate(5, 5)));
    }

    @Test
    void shouldThrowErrorWhenCoordinateIsNegative() {
        Game newGame = new Game();
        assertThrows(InvalidArgumentException.class, () -> newGame.addCoordinate(-1, 1));
    }

    @Test
    void shouldReturnIncompleteShapeWhenNumberOfCoordinatesAreLessThanThree() {
        Game newGame = new Game();
        newGame.addCoordinate(1, 1);

        assertTrue(newGame.getCoordinate(1).equal(new Coordinate(1, 1)));
        assertFalse(newGame.isShapeCompleted());
    }
}