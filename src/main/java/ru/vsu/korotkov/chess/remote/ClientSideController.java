package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.fx.Piece;
import ru.vsu.korotkov.chess.move.MoveResult;

public interface ClientSideController {
    Figure[][] getField();
    void notifyClick(Coord[] coord);// все лиссенеры выполнитесь
    MoveResult getUpdate();
}
