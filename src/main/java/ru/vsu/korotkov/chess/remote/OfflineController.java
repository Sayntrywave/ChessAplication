package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.listeners.ClientFieldListener;
import ru.vsu.korotkov.chess.listeners.ClientMoveListener;
import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.players.PlayerType;

import java.util.ArrayList;
import java.util.List;

public class OfflineController implements ClientSideController, ServerSideController {

    List<ClientMoveListener> clientMoveListeners = new ArrayList<>();
    List<ClientFieldListener> clientFieldListeners = new ArrayList<>();
    private IGameController gameController;
    private Game game;
    private Coord[] move;
    private Figure[][] gameField;
    //создать лиссенеры, что ход пришел то обновить
    //при чем в обе стороны

    public OfflineController(IGameController gameController) {
        //todo main game not abstract
        this.gameController = gameController;
        this.game = new Game(PlayerType.HUMAN,PlayerType.HUMAN,this);
        new Thread(game::start).start();
    }

    @Override
    public Figure[][] getField() {

        return gameField;
    }

    @Override
    public void notifyUpdate(MoveResult result) {
        gameController.movePiece(result);
    }

    @Override
    public MoveResult getUpdate() {
        return null;
    }

    @Override
    public void notifyClick(Coord[] coord) {
        synchronized (this) {
                move = coord;
                this.notifyAll();
        }
        //тут вызываем getMove и всех лиссенеров пробуждаем
    }

    @Override
    public void sendGameField(Figure[][] figures) {
        gameField = figures;
        getField();
    }

    @Override
    public Coord[] askClient() {
        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return move;
    }
}
