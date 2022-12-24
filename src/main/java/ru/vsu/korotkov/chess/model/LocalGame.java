package ru.vsu.korotkov.chess.model;

import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.players.Human;
import ru.vsu.korotkov.chess.players.PlayerType;

public class LocalGame extends  Game{

    public LocalGame(PlayerType player1, PlayerType player2) {
        super(player1);
        setPlayer(player2,false);
    }

    @Override
    public void onRoundFinished(MoveType moveType,Coord[] coords) {
//        nothing to do
    }
}
