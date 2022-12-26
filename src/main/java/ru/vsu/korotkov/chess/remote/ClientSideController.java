package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.fx.Piece;

public interface ClientSideController {
    Figure[][] getField();
    void notifyClick(Coord[] coord);
}
