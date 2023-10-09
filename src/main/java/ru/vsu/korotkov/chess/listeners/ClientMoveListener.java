package ru.vsu.korotkov.chess.listeners;

import ru.vsu.korotkov.chess.move.MoveResult;

public interface ClientMoveListener {
    void setMoveResult(MoveResult moveResult);
}
