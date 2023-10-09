package ru.vsu.korotkov.chess.listeners;

import ru.vsu.korotkov.chess.figures.Figure;

public interface ClientFieldListener {
    void setGameField(Figure[][] gameField);
}
