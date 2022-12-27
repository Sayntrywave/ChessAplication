package ru.vsu.korotkov.chess;

import ru.vsu.korotkov.chess.console.ConsoleGame;
import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.server.ChessParser;

public class Main {
    public static void main(String[] args) {
//        ConsoleGame consoleGame = new ConsoleGame();
//        consoleGame.start();
        String field = "[WR, WK, WB, WQ, WKG, WB, WK, WR]\n[WP, WP, WP, WP, WP, WP, WP, WP]\nnull, null, null, null, null, null, null, null]\nnull, null, null, null, null, null, null, null]\n[null, null, null, null, null, null, null, null]\n[null, null, null, null, null, null, null, null]\nBP, BP, BP, BP, BP, BP, BP, BP]\nBR, BK, BB, BQ, BKG, BB, BK, BR]\n";
        System.out.println(field);
        //        ChessParser.getField(args.toString());
    }

}