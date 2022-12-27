package ru.vsu.korotkov.chess.move;

import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;

public record MoveResult(MoveType moveType, Coord coord1, Coord coord2) {
}
