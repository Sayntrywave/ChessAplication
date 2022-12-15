package ru.vsu.korotkov.chess.figures;

import ru.vsu.korotkov.chess.userInteractions.UI.PieceType;

public class Pawn extends Figure {

//    private boolean isWhite =false;
    private boolean isMoved = false;

/*
    public Pawn(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
    }
*/
/*
    public Pawn(boolean isWhite, Figure[][] gameField, King king) {
        super(isWhite, gameField, king);
    }*/

    public Pawn(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        super(isWhite, gameField, coord, king);
        pieceType = PieceType.PAWN;
    }

    public Pawn(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
        pieceType = PieceType.PAWN;
    }
    /*  @Override
    public boolean move(int x, int y) {

    }*/

    @Override
    public boolean canMove(int x, int y) {
        if (isWhite){
            if( y - coord.y ==1 && Math.abs(x - coord.x) == 1 && gameField[y][x] != null){
                return true;
            }
            if (y - coord.y == 2){
                if (y - 1 < 0){
                    return false;
                }
                if (!isMoved &&  gameField[y][x] == null && gameField[y-1][x] == null){
                    isMoved = true;
                    return true;
                }
            }
            else if (y - coord.y == 1){
                return x == coord.x && gameField[y][x] == null;

            }
        }
        else {
            if( coord.y - y ==1 && Math.abs(x - coord.x) == 1 && gameField[y][x] != null){
                return true;
            }
            if ( coord.y - y == 2){
                if (y - 1 < 0){
                    return false;
                }
                if (!isMoved &&  gameField[y][x] == null && gameField[y+1][x] == null){
                    isMoved = true;
                    return true;
                }
            }
            else if ( coord.y - y == 1){
                return x == coord.x && gameField[y][x] == null;

            }
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "P";
    }
}
