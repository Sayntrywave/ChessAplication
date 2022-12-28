package ru.vsu.korotkov.chess.console;

import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.remote.ClientSideController;
import ru.vsu.korotkov.chess.remote.IGameController;

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
        controller.addClientMoveListener(moveResult -> cli.updateGameField());
        controller.addClientFieldListener(gameField -> {
            if (cli != null) {
                return;
            }
            System.out.println("я получил поле");
            cli = new CLI(gameField);
            controller.startGame();
            cli.updateGameField();
            while (true) {
                controller.notifyClick(cli.getMove());
            }
        });


    }
}
