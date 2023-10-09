package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.listeners.GameMoveListener;
import ru.vsu.korotkov.chess.move.MoveResult;

public interface ServerSideController {
    //    void getMove();//попробовать заменить просто добавив поле для лиссенеров
    void notifyUpdate(MoveResult move); // все лиссенеры обновитесь: команда - лиссенерс апдейт

    void sendGameField(Figure[][] figures);

    void addGameMoveListener(GameMoveListener gameMoveListener);
}
