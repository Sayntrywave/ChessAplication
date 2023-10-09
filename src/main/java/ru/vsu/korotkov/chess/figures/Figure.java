package ru.vsu.korotkov.chess.figures;

import ru.vsu.korotkov.chess.enums.PieceType;

public abstract class Figure {

    public final boolean isWhite;
    protected Coord coord;
    protected King king;
    Figure[][] gameField;
    PieceType pieceType;

    public Figure(boolean isWhite, Figure[][] gameField, Coord coord, King king) {
        this.isWhite = isWhite;
        this.gameField = gameField;
        this.coord = coord;
        this.king = king;

    }

    public Figure(boolean isWhite, Figure[][] gameField, Coord coord) {
        //If you use this constructor, then you are sure that
        // the king is in position as at the beginning of the game
        this.isWhite = isWhite;
        this.gameField = gameField;
        this.coord = coord;
        this.king = (King) (isWhite ? gameField[0][4] : gameField[7][4]);

    }


    public Coord getCoord() {
        return coord;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public abstract boolean canMove(int x, int y);

    public boolean moveTo(int x, int y) {
        if (canMove(x, y)) {
            Figure figure = gameField[y][x];
            gameField[y][x] = gameField[this.getCoord().getY()][this.getCoord().getX()];
            gameField[this.getCoord().getY()][this.getCoord().getX()] = null;
            // king mustn't moved
            if (king.isChecked()) {
                gameField[this.getCoord().getY()][this.getCoord().getX()] = gameField[y][x];
                gameField[y][x] = figure;
                return false;
            }

            coord.x = x;
            coord.y = y;
            return true;
        }
        return false;
    }

    protected boolean move(int x, int y, int drX, int drY) {
        for (int i = 1; Math.max(coord.x + drX * i, coord.y + drY * i) < gameField.length && Math.min(coord.x + drX * i, coord.y + drY * i) >= 0; i++) {
            if (gameField[coord.y + drY * i][coord.x + drX * i] != null && gameField[coord.y + drY * i][coord.x + drX * i].isWhite == isWhite) {
                return false;
            }
            if (coord.y + drY * i == y && coord.x + drX * i == x) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return isWhite ? "W" : "B";
    }
}
