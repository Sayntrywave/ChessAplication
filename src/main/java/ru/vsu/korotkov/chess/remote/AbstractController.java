package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.listeners.ClientFieldListener;
import ru.vsu.korotkov.chess.listeners.ClientMoveListener;
import ru.vsu.korotkov.chess.listeners.GameMoveListener;
import ru.vsu.korotkov.chess.move.MoveResult;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController implements ClientSideController, ServerSideController {
    List<ClientMoveListener> clientMoveListeners = new ArrayList<>();
    List<ClientFieldListener> clientFieldListeners = new ArrayList<>();
    List<GameMoveListener> gameMoveListeners = new ArrayList<>();


    @Override
    public void addClientMoveListener(ClientMoveListener moveListener) {
        clientMoveListeners.add(moveListener);
    }

    @Override
    public void addClientFieldListener(ClientFieldListener fieldListener) {
        clientFieldListeners.add(fieldListener);
    }

    @Override
    public void addGameMoveListener(GameMoveListener gameMoveListener) {
        gameMoveListeners.add(gameMoveListener);
    }

    @Override
    public abstract void notifyClick(Coord[] coord);

    @Override
    public abstract void startGame();

    @Override
    public abstract void notifyUpdate(MoveResult move);

    @Override
    public abstract void sendGameField(Figure[][] figures);


}
