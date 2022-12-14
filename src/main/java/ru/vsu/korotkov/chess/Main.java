package ru.vsu.korotkov.chess;

import ru.vsu.korotkov.chess.userInteractions.CLI;

public class Main {
    public static void main(String[] args) {
        Game chess = new Game();
        UserInteraction userInteraction = new CLI();
        chess.start(userInteraction);
    }

}