package ru.vsu.korotkov.chess;

import ru.vsu.korotkov.chess.userInteractions.CLI;
import ru.vsu.korotkov.chess.userInteractions.UI.ChessApp;
import ru.vsu.korotkov.chess.userInteractions.UI.GUI;

public class Main {
    public static void main(String[] args) {
        Game chess = new Game();
//        UserInteraction userInteraction = new CLI();
        UserInteraction userInteraction = new GUI(new ChessApp());
        chess.start(userInteraction);
    }

}