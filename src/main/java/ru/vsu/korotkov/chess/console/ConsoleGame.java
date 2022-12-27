package ru.vsu.korotkov.chess.console;

import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.listeners.ClientFieldListener;
import ru.vsu.korotkov.chess.listeners.ClientMoveListener;
import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.events.GameOverListener;
import ru.vsu.korotkov.chess.events.RoundEventListeners;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.remote.ClientSideController;
import ru.vsu.korotkov.chess.remote.IGameController;
import ru.vsu.korotkov.chess.remote.OfflineController;
import ru.vsu.korotkov.chess.remote.ServerSideController;

public class ConsoleGame implements IGameController {
    ClientSideController controller;
    CLI cli;
    public ConsoleGame(ClientSideController controller) {
        this.controller = controller;
    }

    @Override
    public void movePiece(MoveResult moveResult) {
        cli.updateGameField();
    }

    public void start() {
        //todo ask user about game
//        cli = new CLI(controller.getField());
//        game.addRoundEventListeners((moveType,coords) -> {
//            if (moveType == MoveType.KILL || moveType == MoveType.NORMAL){
//                cli.updateGameField();
//            }
//        });
//        game.addGameOverListeners(() -> System.out.println("Game over"));
        controller.addClientMoveListener(moveResult -> cli.updateGameField());
        controller.addClientFieldListener(gameField -> {
            if (cli != null){
                return;
            }
            System.out.println("я получил поле");
            cli = new CLI(gameField);
            controller.startGame();
            cli.updateGameField();
            while (true){ //todo make method isGameOver
                controller.notifyClick(cli.getMove());
            }
        });




    }
}
