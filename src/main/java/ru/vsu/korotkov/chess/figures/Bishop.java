package ru.vsu.korotkov.chess.figures;

import ru.vsu.korotkov.chess.enums.PieceType;

public class Bishop extends Figure {

    public Bishop(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        super(isWhite, gameField, coord, king);
        if (isWhite) {
            pieceType = PieceType.WKNIGHT;
        } else pieceType = PieceType.BKNIGHT;
    }

    public Bishop(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
        if (isWhite) {
            pieceType = PieceType.WBISHOPS;
        } else pieceType = PieceType.BBISHOPS;
    }

    @Override
    public boolean canMove(int x, int y) {
        if (y == coord.getY() || x == coord.getX()) {
            return false;
        }
        if (!(Math.abs(y - coord.getY()) == Math.abs(x - coord.getX()))) {
            return false;
        }
        return move(x, y, Integer.compare(x, coord.x), Integer.compare(y, coord.y));

    }

    @Override
    public String toString() {
        return super.toString() + "B";
    }
}
