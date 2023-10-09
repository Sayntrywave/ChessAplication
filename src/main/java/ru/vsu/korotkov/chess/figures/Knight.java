package ru.vsu.korotkov.chess.figures;

import ru.vsu.korotkov.chess.enums.PieceType;

public class Knight extends Figure {


    public Knight(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        super(isWhite, gameField, coord, king);
        if (isWhite) {
            pieceType = PieceType.WKNIGHT;
        } else pieceType = PieceType.BKNIGHT;
    }

    public Knight(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
        if (isWhite) {
            pieceType = PieceType.WKNIGHT;
        } else pieceType = PieceType.BKNIGHT;
    }

    @Override
    public boolean canMove(int x, int y) {
        return (Math.abs(x - coord.x) == 1 && Math.abs(y - coord.y) == 2) || (Math.abs(x - coord.x) == 2 && Math.abs(y - coord.y) == 1);

    }

    @Override
    public String toString() {
        return super.toString() + "K";
    }
}
