package ru.vsu.korotkov.chess.console;

import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.events.GameOverListener;
import ru.vsu.korotkov.chess.events.RoundEventListeners;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.remote.ClientSideController;
import ru.vsu.korotkov.chess.remote.IGameController;
import ru.vsu.korotkov.chess.remote.OfflineController;

public class ConsoleGame implements IGameController {
    ClientSideController controller;
    CLI cli;
    public ConsoleGame() {
    }

    @Override
    public void movePiece(MoveResult moveResult) {
        cli.updateGameField();
    }

    public void start() {
        //todo ask user about game
        controller = new OfflineController(this);
        cli = new CLI(controller.getField());
//        game.addRoundEventListeners((moveType,coords) -> {
//            if (moveType == MoveType.KILL || moveType == MoveType.NORMAL){
//                cli.updateGameField();
//            }
//        });
//        game.addGameOverListeners(() -> System.out.println("Game over"));

        cli.updateGameField();
        while (true){ //todo make method isGameOver
            controller.notifyClick(cli.getMove());
        }
    }
}
