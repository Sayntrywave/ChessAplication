package ru.vsu.korotkov.chess;

import ru.vsu.korotkov.chess.console.ConsoleGame;
import ru.vsu.korotkov.chess.model.Game;

public class Main {
    public static void main(String[] args) {
        Game chess = new Game();
        ConsoleGame consoleGame = new ConsoleGame(chess);
        consoleGame.start();
    }

}