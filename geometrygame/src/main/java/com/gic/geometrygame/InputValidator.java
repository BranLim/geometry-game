package com.gic.geometrygame;

public class InputValidator {

    private final static String pattern = "\\d+ \\d+";
    private final static String quitGamePattern = ".*#.*";

    public static boolean isCoordinateInput(String value){
        return value.matches(pattern);
    }

    public static boolean isQuitInput(String value){
        return value.matches(quitGamePattern);
    }
}
