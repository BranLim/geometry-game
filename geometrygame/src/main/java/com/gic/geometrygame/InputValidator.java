package com.gic.geometrygame;

public class InputValidator {

    private final static String coordinatePattern = "\\d+ \\d+";
    private final static String poundCharacter = ".*#.*";

    public static boolean isCoordinateInput(String value){
        return value.matches(coordinatePattern);
    }

    public static boolean isPoundInput(String value){
        return value.matches(poundCharacter);
    }
}
