package ru.vsu.korotkov.chess.server;

import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.figures.*;
import ru.vsu.korotkov.chess.move.MoveResult;

public class ChessParser {
    public static Figure[][] getField(String string){
        StringBuilder stringBuilder = new StringBuilder(string);
        Figure[][] gameField = new Figure[8][8];

        gameField[0][4] = new King(true, gameField, new Coord(4, 0));


        gameField[7][4] = new King(false, gameField, new Coord(4, 7));

        gameField[0][0] = new Rook(true, gameField, new Coord(0, 0));
        gameField[0][7] = new Rook(true, gameField, new Coord(7, 0));
        gameField[7][0] = new Rook(false, gameField, new Coord(0, 7));
        gameField[7][7] = new Rook(false, gameField, new Coord(7, 7));

        gameField[0][1] = new Knight(true, gameField, new Coord(1, 0));
        gameField[0][6] = new Knight(true, gameField, new Coord(6, 0));
        gameField[7][1] = new Knight(false, gameField, new Coord(1, 7));
        gameField[7][6] = new Knight(false, gameField, new Coord(6, 7));

        gameField[0][2] = new Bishop(true, gameField, new Coord(2, 0));
        gameField[0][5] = new Bishop(true, gameField, new Coord(5, 0));
        gameField[7][2] = new Bishop(false, gameField, new Coord(2, 7));
        gameField[7][5] = new Bishop(false, gameField, new Coord(5, 7));

        gameField[0][3] = new Queen(true, gameField, new Coord(3, 0));
        gameField[7][3] = new Queen(false, gameField, new Coord(3, 7));


        for (int j = 0; j < 8; j++) {
            gameField[1][j] = new Pawn(true, gameField, new Coord(j, 1));
        }
        for (int j = 0; j < 8; j++) {
            gameField[6][j] = new Pawn(false, gameField, new Coord(j, 6));
        }
        return gameField;
    }
    public static Coord[] getCoord(String string){
        return new Coord[]{
                new Coord(3,1),
                new Coord(3,3)
        };
    }
    public static MoveResult getMoveResult(String string){
        return new MoveResult(MoveType.NONE,
                new Coord(3,1),
                new Coord(3,3));
    }
}
