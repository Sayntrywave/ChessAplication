package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.listeners.ClientFieldListener;
import ru.vsu.korotkov.chess.listeners.ClientMoveListener;
import ru.vsu.korotkov.chess.listeners.GameMoveListener;
import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.players.PlayerType;

import java.util.ArrayList;
import java.util.List;

public class OfflineController implements ClientSideController, ServerSideController {

    List<ClientMoveListener> clientMoveListeners = new ArrayList<>();
    List<ClientFieldListener> clientFieldListeners = new ArrayList<>();
    List<GameMoveListener> gameMoveListeners = new ArrayList<>();
    private Game game;

    public OfflineController() {
        //todo main game not abstract
        this.game = new Game();
        game.setPlayer(PlayerType.HUMAN, true, this);
        game.setPlayer(PlayerType.HUMAN, false, this);
    }

    @Override
    public void startGame() {
        game.start();
    }

    @Override
    public void notifyUpdate(MoveResult result) {
        clientMoveListeners.forEach(l -> l.setMoveResult(result));
    }


    @Override
    public void addClientMoveListener(ClientMoveListener moveListener) {
        clientMoveListeners.add(moveListener);
    }

    @Override
    public void addClientFieldListener(ClientFieldListener fieldListener) {
        clientFieldListeners.add(fieldListener);
    }

    @Override
    public void notifyClick(Coord[] coord) {
        gameMoveListeners.forEach(l -> l.makeMove(coord));

    }

    @Override
    public void sendGameField(Figure[][] figures) {
        clientFieldListeners.forEach(l -> l.setGameField(figures));
    }

    @Override
    public void addGameMoveListener(GameMoveListener gameMoveListener) {
        gameMoveListeners.add(gameMoveListener);
    }

}
