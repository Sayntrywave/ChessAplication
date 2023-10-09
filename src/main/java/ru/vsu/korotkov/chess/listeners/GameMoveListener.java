package ru.vsu.korotkov.chess.listeners;

import ru.vsu.korotkov.chess.figures.Coord;

public interface GameMoveListener {
    void makeMove(Coord[] coords);
}
