package com.gic.geometrygame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    @Test
    void shouldReturnShapeAfterCompleteAndFinalised() {
        var newGame = new Game();
        newGame.addCoordinate(1, 1);
        newGame.addCoordinate(5, 1);
        newGame.addCoordinate(5, 5);
        newGame.finaliseShape();

        assertTrue(newGame.isShapeComplete());
        assertTrue(newGame.isShapeFinalised());
        assertEquals("1:(1,1)\n2:(5,1)\n3:(5,5)", newGame.getShape());
    }
}
