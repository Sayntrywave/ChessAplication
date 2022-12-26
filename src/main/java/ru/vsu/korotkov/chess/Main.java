package ru.vsu.korotkov.chess;

import ru.vsu.korotkov.chess.console.ConsoleGame;
import ru.vsu.korotkov.chess.model.Game;

public class Main {
    public static void main(String[] args) {
        ConsoleGame consoleGame = new ConsoleGame();
        consoleGame.start();
    }

}