package ru.vsu.korotkov.chess.figures;

import ru.vsu.korotkov.chess.enums.PieceType;

public class Rook extends Figure {

    public Rook(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        super(isWhite, gameField, coord, king);
        if (isWhite) {
            pieceType = PieceType.WKNIGHT;
        } else pieceType = PieceType.BKNIGHT;
    }

    public Rook(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
        if (isWhite) {
            pieceType = PieceType.WROOK;
        } else pieceType = PieceType.BROOK;
    }

    @Override
    public boolean canMove(int x, int y) {
        if (x == coord.x || y == coord.y) {
            return move(x, y, Integer.compare(x, coord.x), Integer.compare(y, coord.y));
        }
        return false;
    }


    @Override
    public String toString() {
        return super.toString() + "R";
    }
}

