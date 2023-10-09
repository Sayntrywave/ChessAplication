package ru.vsu.korotkov.chess;

import ru.vsu.korotkov.chess.console.ConsoleGame;
import ru.vsu.korotkov.chess.remote.OnlineController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleGame consoleGame = new ConsoleGame(new OnlineController(9999));
        consoleGame.start();
    }

}