package ru.vsu.korotkov.chess.remote;

import javafx.application.Platform;
import ru.vsu.korotkov.chess.console.GameCommand;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.listeners.ClientFieldListener;
import ru.vsu.korotkov.chess.listeners.ClientMoveListener;
import ru.vsu.korotkov.chess.listeners.GameMoveListener;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.server.ChessParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlineController extends AbstractController {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private GameCommand currCommand;



    public OnlineController(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);

        new Thread(this::run).start();
    }
    public OnlineController(int port) throws IOException {
        this(new Socket("localhost",port));
    }

    @Override
    public void startGame() {
        //there's nothing to do
    }




    @Override
    public void notifyClick(Coord[] coord) {
        //todo print command coord
        send(GameCommand.GCOORD,Arrays.toString(coord));
    }



    @Override
    public void notifyUpdate(MoveResult move) {
        send(GameCommand.GMOVERESULT,move.toString());
    }

    @Override
    public void sendGameField(Figure[][] figures) {
        StringBuilder message = new StringBuilder();
        for (Figure[] figure : figures) {
            message.append(Arrays.toString(figure)).append("\n");
        }
        send(GameCommand.FIELD,message.toString());
    }

    private void send(GameCommand gameCommand, String message){
        System.out.println("я отправил" + gameCommand.toString());
        out.println(gameCommand + ":");
        out.println(message);
    }

    private void run() {
        while (true) {
            try {
//                System.out.println(in.readLine());
                String command = in.readLine().split(":")[0];
                if (command.equals(GameCommand.FIELD.toString())){
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < 8; i++) {
                        stringBuilder.append(in.readLine());
                    }
                    Figure[][] figures = ChessParser.getField(command);
                    Platform.runLater(() -> clientFieldListeners.forEach(l -> l.setGameField(figures)));
                }
                else if(command.equals(GameCommand.GCOORD.toString())){
                    Coord[] coords = ChessParser.getCoord(command);
                    gameMoveListeners.forEach(l -> l.makeMove(coords));
                }
                else if (command.equals(GameCommand.GMOVERESULT.toString())){
                    MoveResult moveResult = ChessParser.getMoveResult(command);
                    Platform.runLater(() ->clientMoveListeners.forEach(l -> l.setMoveResult(moveResult)));
                }
                else out.println("error command, try again");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }





    //    @Override
    public void getField() {
        //todo get field
        out.println("getField");

        new Thread(() -> {
            //todo if command == field then break
            while (true) {
                try {
                    System.out.println(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // do stuff
            }
        }).start();

        // get info from in.readLine();
    }

    //    @Override
    public void getUpdate() {
    }
    //    @Override
    public void getMove() {
        new Thread(() -> {
            //todo if command == coord then break
            while (true) {
                try {
                    System.out.println(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // do stuff
            }
        }).start();
        // get info from in.readLine();
    }

}



