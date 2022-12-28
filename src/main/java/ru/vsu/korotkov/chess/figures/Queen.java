package ru.vsu.korotkov.chess.figures;

import ru.vsu.korotkov.chess.enums.PieceType;

public class Queen extends Figure {

    public Queen(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        super(isWhite, gameField, coord, king);
        if (isWhite) {
            pieceType = PieceType.WKNIGHT;
        } else pieceType = PieceType.BKNIGHT;
    }

    public Queen(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
        if (isWhite) {
            pieceType = PieceType.WQUEEN;
        } else pieceType = PieceType.BQUEEN;
    }

    @Override
    public boolean canMove(int x, int y) {
        return move(x, y, Integer.compare(x, coord.x), Integer.compare(y, coord.y));
    }


    @Override
    public String toString() {
        return super.toString() + "Q";
    }
}

