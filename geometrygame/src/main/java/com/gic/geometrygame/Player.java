package com.gic.geometrygame;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

    private final String pattern = "\\d+ \\d+";

    private Scanner scanner = new Scanner(System.in);
    private PlayState state = PlayState.START;

    public void play(Game game) {
        if (game == null) {
            throw new IllegalStateException("Game is not ready");
        }
        boolean quit = false;
        while (!quit) {
            switch (state) {
                case START:
                    handleMenu(scanner);
                    break;
                case SHAPE_CREATION_MANUAL:
                    handleShapeCreation(scanner, game);
                    break;
                case SHAPE_CREATION_RANDOM:
                    handleCreateRandomShape(scanner, game);
                    break;
                case PLAYING:
                    handlePlayingState(scanner);
                    break;
                case END:
                    quit = true;
                    break;
            }
        }
    }

    private void handleMenu(Scanner scanner) {
        System.out.println("Welcome to the GIC geometry puzzle app");
        System.out.println("[1] Create a custom shape");
        System.out.println("[2] Generate a random shape");

        try {
            String value = scanner.nextLine();
            if (value.equals("#")) {
                state = PlayState.END;
                return;
            }
            int option = Integer.parseInt(value);
            state = switch (option) {
                case 1 -> PlayState.SHAPE_CREATION_MANUAL;
                case 2 -> PlayState.SHAPE_CREATION_RANDOM;
                default -> throw new InputMismatchException();
            };

        } catch (InputMismatchException e) {
            System.out.println("Invalid option.");
            state = PlayState.START;
        } catch (NumberFormatException e) {
            System.out.println("Invalid option.");
            state = PlayState.START;
        }
    }

    private void handleShapeCreation(Scanner scanner, Game game) {

        System.out.println(String.format("Please enter coordinate %d in x y format", game.getCoordinateCount() + 1));
        String value = scanner.nextLine();

        if (!value.matches(pattern)) {
            System.out.println("Invalid input.");
            return;
        }

        int[] coordinates = Arrays.stream(value.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        try {
            game.addCoordinate(coordinates[0], coordinates[1]);

        } catch (IllegalArgumentException e) {
            System.out.println(String.format("New coordinates(%d, %d) is invalid!!!", coordinates[0], coordinates[1]));
        }

    }

    private void handleCreateRandomShape(Scanner scanner, Game game) {
        game.createRandomShape();
        System.out.println("Your random shape is");
        System.out.println(game.getShape());
        state = PlayState.PLAYING;

    }

    private void handlePlayingState(Scanner scanner) {

    }
}
