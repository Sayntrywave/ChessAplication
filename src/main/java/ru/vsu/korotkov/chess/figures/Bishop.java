package ru.vsu.korotkov.chess.figures;

import ru.vsu.korotkov.chess.userInteractions.UI.PieceType;

public class Bishop extends Figure {


//    public Bishop(boolean isWhite, Figure[][] gameField, Coord coord) {
//        super(isWhite, gameField, coord);
//    }


/*    public Bishop(boolean isWhite, Figure[][] gameField, King king) {
        super(isWhite, gameField, king);
    }*/

    public Bishop(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        super(isWhite, gameField, coord, king);
        pieceType = PieceType.BISHOPS;
    }

    public Bishop(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
        pieceType = PieceType.BISHOPS;
    }

    @Override
    public boolean canMove(int x, int y) {
        if (y == coord.getY() || x == coord.getX()){
            return false;
        }
        if (!(Math.abs(y - coord.getY()) == Math.abs(x - coord.getX()))){
            return false;
        }
        return move(x,y,Integer.compare(x,coord.x), Integer.compare(y,coord.y));

    }
/*    private boolean move(int x, int y, int incX,int incY){
        for (int i = 1; Math.max(coord.x +incX* i,coord.y+ incY*i) < gameField.length && Math.min(coord.x+incX*i,coord.y+incY*i) > 0; i++) {
            if(coord.y+incY*i == y && coord.x+incX*i == x){
                return true;
            }
            if (gameField[coord.y+incY*i][coord.x+incX*i] != null){
                return false;
            }
        }
        return false;
    }*/

    @Override
    public String toString() {
        return super.toString() + "B";
    }
}
