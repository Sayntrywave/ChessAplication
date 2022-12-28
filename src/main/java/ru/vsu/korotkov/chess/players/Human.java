package ru.vsu.korotkov.chess.players;

import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.remote.ServerSideController;

public class Human extends Player {
    public Human(boolean isWhite, Figure[][] gameField, ServerSideController serverSideController) {
        super(isWhite, gameField, serverSideController);
    }
}
