package ru.vsu.korotkov.chess.userInteractions.UI;

import javafx.stage.Stage;
import ru.vsu.korotkov.chess.UserInteraction;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;

public class GUI implements UserInteraction {

    ChessApp chessApp;

    public GUI(ChessApp chessApp) {
        this.chessApp = chessApp;
//        chessApp.start(new Stage());
    }

    @Override
    public void updateGameField() {

    }

    @Override
    public Coord[] getMove() {
        return new Coord[0];
    }

    @Override
    public void printWinner(int numberOfMoves) {

    }

    @Override
    public void setGameField(Figure[][] gameField) {

    }
}
