package ru.vsu.korotkov.chess.figures;

import ru.vsu.korotkov.chess.enums.PieceType;

public abstract class Figure {

    public final boolean isWhite;
    Figure[][] gameField;
    protected Coord coord;
    protected King king;
    PieceType pieceType;
    //enemy's King
/*    class Coord{
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }*/

    public Figure(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        this.isWhite = isWhite;
        this.gameField = gameField;
        this.coord = coord;
        this.king = king;

       /* if(isWhite){
            if (coord.equals(new Coord(3,0))){//check king
                king = coord;
            }
            else {
                king.coord = gameField[0][3].coord;
            }
        }
        else {
            if (coord.equals(new Coord(4,7))){//check king
                king.coord = coord;
            }
            else {
                king.coord = gameField[7][4].coord;
            }
        }*/
    }
    public Figure(boolean isWhite, Figure[][] gameField, Coord coord) {
        //If you use this constructor, then you are sure that
        // the king is in position as at the beginning of the game
        this.isWhite = isWhite;
        this.gameField = gameField;
        this.coord = coord;
        this.king = (King) (isWhite ?  gameField[0][4]: gameField[7][4]);

       /* if(isWhite){
            if (coord.equals(new Coord(3,0))){//check king
                king = coord;
            }
            else {
                king.coord = gameField[0][3].coord;
            }
        }
        else {
            if (coord.equals(new Coord(4,7))){//check king
                king.coord = coord;
            }
            else {
                king.coord = gameField[7][4].coord;
            }
        }*/
    }

/*    public boolean isWhite() {
        return isWhite;
    }*/

    public Coord getCoord() {
        return coord;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
    /*
    protected void setCoord(int x, int y) throws Exception {
        if (x < 0 || y < 0  || y >= gameField.length || x>= gameField[0].length ){
            throw new Exception("Переместить фигуру на координаты x: " + x + " y:" + y + "не удалось");
        }
        this.coord = new Coord(x,y);
    }*/

    public abstract boolean canMove(int x, int y);

    public boolean moveTo(int x, int y){
        if (canMove(x,y)){
            Figure figure = gameField[y][x];
            gameField[y][x] = gameField[this.getCoord().getY()][this.getCoord().getX()];
            gameField[this.getCoord().getY()][this.getCoord().getX()] = null;
            // king mustn't moved
            if(king.isChecked()){
                gameField[this.getCoord().getY()][this.getCoord().getX()] = gameField[y][x];
                gameField[y][x] = figure;
                return false;
            }

           // System.out.println(king.isChecked());

            coord.x = x;
            coord.y = y;
            return true;
        }
        return false;
    }

    protected boolean move(int x, int y, int drX, int drY){
        for (int i = 1; Math.max(coord.x +drX* i,coord.y+ drY*i) < gameField.length && Math.min(coord.x+drX*i,coord.y+drY*i) >= 0; i++) {
            if (gameField[coord.y+drY*i][coord.x+drX*i] != null && gameField[coord.y+drY*i][coord.x+drX*i].isWhite == isWhite){
                return false;
            }
            if(coord.y+drY*i == y && coord.x+drX*i == x){
                return true;
            }
        }
        return false;
    }

   /* protected boolean willKingBeChecked(int x, int y){
        int drX = Integer.compare(coord.x, king.coord.x);
        int drY = Integer.compare(coord.y, king.coord.y);

        if(!(  (y == coord.getY() || x == coord.getX()) || Math.abs(y - coord.getY()) == Math.abs(x - coord.getX()) )){
            return false;
        }

        Figure figure;

        int currX = coord.x ;
        int currY = coord.y ;

        while (currX != king.coord.x  ||  currY != king.coord.y ){
            currX += -1*drX;
            currY += -1*drY;

            figure = gameField[currY][currX];

            if (figure == null){
                continue;
            }
            else {
                if(figure.toString().equals("K")  && figure.isWhite){
                    break;
                }
                return false;
            }


        }


        for (int i = 1; Math.max(coord.x +drX* i,coord.y+ drY*i) < gameField.length && Math.min(coord.x+drX*i,coord.y+drY*i) >= 0; i++) {
            figure = gameField[coord.y+drY*i][coord.x+drX*i];
            if (figure == null){
                continue;
            }

            if (figure.isWhite == this.isWhite){
                break;
            }
            if (figure.toString().equals("Q")){
                return true;
            }
            if (Math.abs(drX) == 1 && Math.abs(drY) == 1){
                if (figure.toString().equals("B") ){
                    return true;
                }
            }

            if ( (drX == 0 && drY !=0) || (drX != 0 && drY != 0)){
                if (figure.toString().equals("R")){
                    return true;
                }
            }

        }
        return false;
    }*/



    @Override
    public String toString() {
        return isWhite ? "W" :"B";
    }
}
