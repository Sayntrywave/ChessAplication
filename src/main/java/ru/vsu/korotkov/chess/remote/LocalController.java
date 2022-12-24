package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.model.LocalGame;
import ru.vsu.korotkov.chess.players.PlayerType;

public class LocalController implements Controller{

    private Game game;

    public LocalController() {
        //todo main game not abstract
        this.game = new LocalGame(PlayerType.HUMAN,PlayerType.HUMAN);
    }

    @Override
    public Figure[][] getField() {
        return game.getGameField();
    }
}
