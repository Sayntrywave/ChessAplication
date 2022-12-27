package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.move.MoveResult;

public interface IGameController {
    void movePiece(MoveResult moveResult);
}
