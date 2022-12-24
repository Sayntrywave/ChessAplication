package ru.vsu.korotkov.chess.console;

import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.events.GameOverListener;
import ru.vsu.korotkov.chess.events.RoundEventListeners;

public class ConsoleGame {
    Game game;
    CLI cli;
    public ConsoleGame(Game chess) {
        this.game = chess;
        cli = new CLI(game.getGameField());
    }

    public void start() {
        game.addRoundEventListeners((moveType,coords) -> {
            if (moveType == MoveType.KILL || moveType == MoveType.NORMAL){
                cli.updateGameField();
            }
        });
        game.addGameOverListeners(() -> System.out.println("Game over"));

        cli.updateGameField();
        while (!game.isOver()){
            game.makeMove(cli.getMove());
        }
    }
}
