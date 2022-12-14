package ru.vsu.korotkov.chess.figures;

public class Knight extends Figure {
  /*  public Knight(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
    }*/

/*    public Knight(boolean isWhite, Figure[][] gameField, King king) {
        super(isWhite, gameField, king);
    }*/

    public Knight(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        super(isWhite, gameField, coord, king);
    }

    public Knight(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
    }
    /*
    @Override
    public boolean move(int x, int y) {
        return true;
    }*/

    @Override
    public boolean canMove(int x, int y) {
        return (Math.abs(x-coord.x) == 1 && Math.abs(y - coord.y) == 2) || (Math.abs(x-coord.x) == 2 && Math.abs(y - coord.y) == 1);

    }

    @Override
    public String toString() {
        return super.toString() + "K";
    }
}
