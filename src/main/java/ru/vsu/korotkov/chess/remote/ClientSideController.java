package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.fx.Piece;
import ru.vsu.korotkov.chess.listeners.ClientFieldListener;
import ru.vsu.korotkov.chess.listeners.ClientMoveListener;
import ru.vsu.korotkov.chess.move.MoveResult;

public interface ClientSideController {
//    void getField();
    void notifyClick(Coord[] coord);// все лиссенеры выполнитесь
//    void getUpdate();
    void addClientMoveListener(ClientMoveListener moveListener);
    void addClientFieldListener(ClientFieldListener fieldListener);
    void startGame();
}
