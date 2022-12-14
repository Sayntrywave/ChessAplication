package ru.vsu.korotkov.chess;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;

public interface UserInteraction {
    void updateGameField();
    Coord[] getMove();
    void printWinner(int numberOfMoves);
    void setGameField(Figure[][] gameField);
}
