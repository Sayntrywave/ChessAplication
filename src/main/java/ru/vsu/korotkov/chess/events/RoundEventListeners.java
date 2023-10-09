package ru.vsu.korotkov.chess.events;

import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.figures.Coord;

public interface RoundEventListeners {
    void onRoundFinished(MoveType moveType, Coord[] coord);
}
