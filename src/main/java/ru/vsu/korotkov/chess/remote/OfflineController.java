package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.fx.GameController;
import ru.vsu.korotkov.chess.fx.Piece;
import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.players.PlayerType;

public class OfflineController implements ClientSideController, ServerSideController {

    private IGameController gameController;
    private Game game;
    private Coord[] move;

    public OfflineController(IGameController gameController) {
        //todo main game not abstract
        this.gameController = gameController;
        this.game = new Game(PlayerType.HUMAN,PlayerType.HUMAN,this);
        new Thread(game::start).start();
    }

    @Override
    public Figure[][] getField() {
        return game.getGameField();
    }

    @Override
    public void notifyUpdate(MoveResult result) {
        gameController.movePiece(result);
    }

    @Override
    public void notifyClick(Coord[] coord) {
        synchronized (this) {
                move = coord;
                this.notifyAll();
        }
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
