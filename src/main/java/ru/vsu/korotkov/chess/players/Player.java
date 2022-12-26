package ru.vsu.korotkov.chess.players;

import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.figures.King;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.remote.ServerSideController;

public abstract class Player {
    private ServerSideController serverSideController;
    public final boolean isWhite;
    private Figure[][] gameField;
    private final King friendlyKing;
//    private final King enemyKing;

    public Player(boolean isWhite, Figure[][] gameField, ServerSideController serverSideController) {
        this.isWhite = isWhite;
        this.gameField = gameField;
        this.serverSideController = serverSideController;

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

        int x = coordinates[0].getX();
        int y = coordinates[0].getY();


        if ( x < 0 || y < 0 || y >= gameField.length || x >= gameField[0].length) {
            System.out.println("Переместить фигуру на координаты x: " + x + " y:" + y + "не удалось");
            return MoveType.NONE;
        }

        Figure figure = gameField[y][x];

        if (figure == null || figure.isWhite == !this.isWhite) {
            return MoveType.NONE;
        }


        int toX = coordinates[1].getX();
        int toY = coordinates[1].getY();
        return moveFigure(figure, toX, toY) ? /*((gameField[toY][toX] == null) ? MoveType.NORMAL : MoveType.KILL) */ MoveType.NORMAL : MoveType.NONE;
    }

    public Coord[] move(){
        return serverSideController.askClient();
    }

    public void updateClient(MoveResult result){
        serverSideController.notifyUpdate(result);
    }



    private boolean moveFigure(Figure figure, int x, int y) {
        return figure.moveTo(x, y);
    }
}
