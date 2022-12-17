package ru.vsu.korotkov.chess.model;

import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.events.GameOverListener;
import ru.vsu.korotkov.chess.events.RoundEventListeners;
import ru.vsu.korotkov.chess.figures.*;
import ru.vsu.korotkov.chess.players.Human;
import ru.vsu.korotkov.chess.players.Player;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private final List<RoundEventListeners> roundEventListeners = new ArrayList<>();
    private final List<GameOverListener> gameOverListeners = new ArrayList<>();
    private final int FieldSize = 8;
    private Figure[][] gameField;
    private final List<Player> players;
    private boolean isOver = false;
    private int numberOfMoves = 0;

    public Game() {//add enum
        createGameField();
        players = new ArrayList<>();
        players.add(new Human(true,gameField));
        players.add(new Human(false,gameField));
    }

    public void setPlayer(Player player) {
        players.add(player);
    }

    public boolean isOver() {
        return isOver;
    }

    private void setGameOver() {
        isOver = true;
        gameOverListeners.forEach(l -> l.onGameOver());
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void  addRoundEventListeners(RoundEventListeners listener){
        roundEventListeners.add(listener);
    }
    public void  addGameOverListeners(GameOverListener listener){
        gameOverListeners.add(listener);
    }

    public Figure[][] getGameField() {
        return gameField;
    }

    public MoveType makeMove(Coord[] coords){
        MoveType moveType;
        switch (players.get(numberOfMoves % 2).moveFigure(coords)){
            //piece can't change gameField, only game can \
            // add cases: kill and normal
            case NORMAL -> {
                numberOfMoves++;
                moveType = MoveType.NORMAL;
            }
            case KILL -> {
                numberOfMoves++;
                moveType = MoveType.KILL;
            }
            case GAMEOVER -> {
                isOver = true;
                moveType = MoveType.GAMEOVER;
                setGameOver();
            }
            default -> {
                moveType = MoveType.NONE;
            }
        }
        roundEventListeners.forEach(l -> l.onRoundFinished(moveType));
        //добавить лиссенеров
        return moveType;
    }

    private void createGameField() {
        gameField = new Figure[FieldSize][FieldSize];


        gameField[0][4] = new King(true, gameField, new Coord(4, 0));


        gameField[7][4] = new King(false, gameField, new Coord(4, 7));

        gameField[0][0] = new Rook(true, gameField, new Coord(0, 0));
        gameField[0][7] = new Rook(true, gameField, new Coord(7, 0));
        gameField[7][0] = new Rook(false, gameField, new Coord(0, 7));
        gameField[7][7] = new Rook(false, gameField, new Coord(7, 7));

        gameField[0][1] = new Knight(true, gameField, new Coord(1, 0));
        gameField[0][6] = new Knight(true, gameField, new Coord(6, 0));
        gameField[7][1] = new Knight(false, gameField, new Coord(1, 7));
        gameField[7][6] = new Knight(false, gameField, new Coord(6, 7));

        gameField[0][2] = new Bishop(true, gameField, new Coord(2, 0));
        gameField[0][5] = new Bishop(true, gameField, new Coord(5, 0));
//        gameField[2][3] = new Bishop(true,gameField,new Coord(3,2);
        gameField[7][2] = new Bishop(false, gameField, new Coord(2, 7));
        gameField[7][5] = new Bishop(false, gameField, new Coord(5, 7));

        gameField[0][3] = new Queen(true, gameField, new Coord(3, 0));
//        gameField[1][3] = new Queen(true,gameField,new Coord(3,1);
        gameField[7][3] = new Queen(false, gameField, new Coord(3, 7));


        for (int j = 0; j < FieldSize; j++) {
            gameField[1][j] = new Pawn(true, gameField, new Coord(j, 1));
        }
        for (int j = 0; j < FieldSize; j++) {
            gameField[6][j] = new Pawn(false, gameField, new Coord(j, 6));
        }


    }
}
