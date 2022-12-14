package ru.vsu.korotkov.chess.figures;

public class Queen extends Figure {


   /* public Queen(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
    }*/

    /*public Queen(boolean isWhite, Figure[][] gameField, King king) {
        super(isWhite, gameField, king);
    }*/

    public Queen(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        super(isWhite, gameField, coord, king);
    }

    public Queen(boolean isWhite, Figure[][] gameField, Coord coord) {
        super(isWhite, gameField, coord);
    }
    /*   @Override
    public boolean move(int x, int y) {

    }*/

    @Override
    public boolean canMove(int x, int y) {
/*        if(Math.abs(coord.x - x) == Math.abs(coord.y - y) && (y == coord.getY() || x == coord.getX()) ){
            return move(x,y,Integer.compare(x,coord.x), Integer.compare(y,coord.y));
        }*/
        return move(x,y,Integer.compare(x,coord.x), Integer.compare(y,coord.y));
    }

 /*   private boolean moveDiagonally(int x, int y, int incX,int incY){
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
        return super.toString() + "Q";
    }
}

