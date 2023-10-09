package ru.vsu.korotkov.chess.console;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;

import java.util.Scanner;

public class CLI {
    Figure[][] gameField;


    public CLI(Figure[][] gameField) {
        this.gameField = gameField;
    }


    public void updateGameField() {
        char a = 'A';
        System.out.print("   ");
        for (int i = 0; i < 8; i++) {
            System.out.print((char) (a + i) + "  ");
        }

        System.out.print("\n   ");
        for (int i = 0; i < 8; i++) {
            System.out.print("-- ");
        }

        System.out.println();
        for (int i = 0; i < gameField.length; i++) {
            System.out.print((i + 1) + "| ");
            for (int j = 0; j < gameField.length; j++) {
                System.out.print(gameField[i][j] == null ? "__" : gameField[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    public Coord[] getMove() {
        Scanner scanner = new Scanner(System.in);
        return parse(scanner.next());
    }


    public void printWinner(int numberOfMoves) {
        System.out.println(numberOfMoves % 2 == 1 ? "FirstPlayer won" : "SecondPlayer won");
    }

    private Coord[] parse(String coordinates) {
        int x = -1;
        int y = -1;

        Coord[] coords = new Coord[2];

        char letter;

        char[] letters = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        String[] stringCoordinates = coordinates.split(":");
        String coordinate;

        for (int i = 0; i < stringCoordinates.length; i++) {
            coordinate = stringCoordinates[i].trim();

            if (coordinate.length() != 2) {
                return null;
            }

            letter = Character.toUpperCase(coordinate.charAt(0));
            for (int j = 0; j < letters.length; j++) {
                if (letter == letters[j]) {
                    x = j;
                    break;
                }
            }
            letter = coordinate.charAt(1);

            if (!Character.isDigit(letter)) {
                return null;
            }
            y = Character.getNumericValue(letter) - 1;

            coords[i] = new Coord(x, y);
        }
        return coords;
    }
}
