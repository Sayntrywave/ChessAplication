package ru.vsu.korotkov.chess;

import ru.vsu.korotkov.chess.figures.*;
import ru.vsu.korotkov.chess.players.Human;
import ru.vsu.korotkov.chess.players.Player;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private Figure[][] gameField;
    private final int FieldSize = 8;
    private List<Player> players;
    private boolean isGameOver = false;


    public void start(UserInteraction userInteraction){
        createGameField();
        userInteraction.setGameField(gameField);
        userInteraction.updateGameField();
        players = new ArrayList<>();


        Player firstPlayer = new Human(true,gameField);
        Player secondPlayer = new Human(false,gameField);

        players.add(firstPlayer);
        players.add(secondPlayer);

       int numberOfMoves = 0;

        while (!isGameOver){
            switch (players.get(numberOfMoves % 2).moveFigure(userInteraction.getMove())) {
                //piece can't change gameField, only game can \
                // add cases: kill and normal
                case NONE -> {
                    System.out.println("Impossible move! Try again");
                    continue;
                }
                case NORMAL -> {
                    numberOfMoves++;
                    userInteraction.updateGameField();
                }
                case GAMEOVER -> {
                    isGameOver = true;
                    break;
                }
            }

        }

        userInteraction.printWinner(numberOfMoves);
    }
    
    private void createGameField(){
        gameField = new Figure[FieldSize][FieldSize];


        gameField[0][4] = new King(true,gameField,new Coord(4,0));


        gameField[7][4] = new King(false,gameField,new Coord(4,7));

        gameField[0][0] = new Rook(true,gameField,new Coord(0,0));
        gameField[0][7] = new Rook(true,gameField,new Coord(7,0));
        gameField[7][0] = new Rook(false,gameField,new Coord(0,7));
        gameField[7][7] = new Rook(false,gameField,new Coord(7,7));

        gameField[0][1] = new Knight(true,gameField,new Coord(1,0));
        gameField[0][6] = new Knight(true,gameField,new Coord(6,0));
        gameField[7][1] = new Knight(false,gameField,new Coord(1,7));
        gameField[7][6] = new Knight(false,gameField,new Coord(6,7));

        gameField[0][2] = new Bishop(true,gameField,new Coord(2,0));
        gameField[0][5] = new Bishop(true,gameField,new Coord(5,0));
//        gameField[2][3] = new Bishop(true,gameField,new Coord(3,2);
        gameField[7][2] = new Bishop(false,gameField,new Coord(2,7));
        gameField[7][5] = new Bishop(false,gameField,new Coord(5,7));

        gameField[0][3] = new Queen(true,gameField,new Coord(3,0));
//        gameField[1][3] = new Queen(true,gameField,new Coord(3,1);
        gameField[7][3] = new Queen(false,gameField,new Coord(3,7));





        for (int j = 0; j < FieldSize ; j++) {
            gameField[1][j] = new Pawn(true,gameField, new Coord(j,1));
        }
        for (int j = 0; j < FieldSize ; j++) {
            gameField[6][j] = new Pawn(false,gameField, new Coord(j,6));
        }


    }
}
