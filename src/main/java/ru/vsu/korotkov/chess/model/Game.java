package ru.vsu.korotkov.chess.model;

import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.events.GameOverListener;
import ru.vsu.korotkov.chess.events.RoundEventListeners;
import ru.vsu.korotkov.chess.figures.*;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.players.Human;
import ru.vsu.korotkov.chess.players.Player;
import ru.vsu.korotkov.chess.players.PlayerType;
import ru.vsu.korotkov.chess.remote.ServerSideController;

import java.util.ArrayList;
import java.util.List;


public class Game {

    private final ServerSideController serverSideController;
    private final List<RoundEventListeners> roundEventListeners = new ArrayList<>();
    private final List<GameOverListener> gameOverListeners = new ArrayList<>();
    private Figure[][] gameField;
    protected final List<Player> players;
    private boolean isOver = false;
    private int numberOfMoves = 0;

    public Game(PlayerType player1, PlayerType player2) {
        this(player1,player2,null);
    }

    public Game(PlayerType player1,PlayerType player2, ServerSideController serverSideController) {
        this.serverSideController = serverSideController;
        createGameField();
        players = new ArrayList<>();
        setPlayer(player1,true, serverSideController);
        setPlayer(player2,false, serverSideController);
//        roundEventListeners.add(this);
        serverSideController.addGameMoveListener(this::makeMove);
    }

    public void setPlayer(PlayerType player, boolean isWhite, ServerSideController serverSideController) {
        if (player.equals(PlayerType.HUMAN)){
            players.add(new Human(isWhite,gameField,serverSideController));
        }
    }

    public boolean isOver() {
        return isOver;
    }

    private void setGameOver() {
        isOver = true;
        gameOverListeners.forEach(GameOverListener::onGameOver);
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

    public boolean getTurn(){
        //Если игрок ходит белыми, то метод возвращает true
        return numberOfMoves % 2 == 1;
    }

    public MoveType makeMove(Coord[] move){
        //todo какой плейер пошел
        int turn = numberOfMoves % 2;
        //todo проверить правильность хода
        MoveType moveType;
        switch (players.get(turn).moveFigure(move)){
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
        players.get(turn).updateClient(new MoveResult(moveType, move[0],move[1]));
        return moveType;
    }

    public void start(){
        serverSideController.sendGameField(gameField);
    }

/*    public MoveType round(Coord[] coord){
        MoveType moveType = doOnEndRound(coord);
        return moveType;
    }*/


    private void createGameField() {
        int fieldSize = 8;

        gameField = new Figure[fieldSize][fieldSize];

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
        gameField[7][2] = new Bishop(false, gameField, new Coord(2, 7));
        gameField[7][5] = new Bishop(false, gameField, new Coord(5, 7));

        gameField[0][3] = new Queen(true, gameField, new Coord(3, 0));
        gameField[7][3] = new Queen(false, gameField, new Coord(3, 7));


        for (int j = 0; j < fieldSize; j++) {
            gameField[1][j] = new Pawn(true, gameField, new Coord(j, 1));
        }
        for (int j = 0; j < fieldSize; j++) {
            gameField[6][j] = new Pawn(false, gameField, new Coord(j, 6));
        }

    }
}
