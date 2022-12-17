package ru.vsu.korotkov.chess.players;

import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.figures.King;

public abstract class Player {
    public final boolean isWhite;
    private Figure[][] gameField;
    private final King friendlyKing;
//    private final King enemyKing;

    public Player(boolean isWhite, Figure[][] gameField) {
        this.isWhite = isWhite;
        this.gameField = gameField;

        friendlyKing = (isWhite) ? (King) gameField[0][4]: (King) gameField[7][4];
//        enemyKing = (!isWhite) ?  (King) gameField[7][4]: (King) gameField[0][3];
    }

    private boolean hasEscapeMoves(){
        Figure figure;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                figure =gameField[i][j];
                if(gameField[i][j] == null){
                    continue;
                }
                if(figure.isWhite == isWhite){
                    for (int k = 0; k < gameField.length; k++) {
                        for (int l = 0; l < gameField[k].length; l++) {
                            if(figure.moveTo(l,k)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public MoveType moveFigure(Coord[] coordinates) {
        if(friendlyKing.isChecked()){
            if(!hasEscapeMoves()){
                return MoveType.GAMEOVER;
            }
        }

        assert coordinates != null;
        if(coordinates.length != 2){
            return MoveType.NONE;
        }


        if (coordinates[0].getX() < 0 || coordinates[0].getY() < 0 || coordinates[0].getY() >= gameField.length || coordinates[0].getX() >= gameField[0].length) {
            System.out.println("Переместить фигуру на координаты x: " + coordinates[0].getX() + " y:" + coordinates[0].getY() + "не удалось");
            return MoveType.NONE;
        }

        Figure figure = gameField[coordinates[0].getY()][coordinates[0].getX()];

        if (figure == null || figure.isWhite == !this.isWhite) {
            return MoveType.NONE;
        }



        return moveFigure(figure, coordinates[1].getX(), coordinates[1].getY()) ? MoveType.NORMAL : MoveType.NONE;
    }



    private boolean moveFigure(Figure figure, int x, int y) {
/*        if( !((isWhite && !gameField[x][y].isWhite || (!isWhite && gameField[x][y].isWhite  ) ) )){
            return false;
            *//*gameField[y][x] = figure;
            gameField[figure.getCoord().getY()][figure.getCoord().getX()] = null;*//*
        }*/
        return figure.moveTo(x, y);
    }
}
