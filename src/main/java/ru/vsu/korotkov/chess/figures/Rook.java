package ru.vsu.korotkov.chess.figures;

public class Rook extends Figure {


/*    public Rook(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
    }*/

/*
    public Rook(boolean isWhite, Figure[][] gameField, King king) {
        super(isWhite, gameField, king);
    }
*/


    public Rook(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        super(isWhite, gameField, coord, king);
    }

    public Rook(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
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

