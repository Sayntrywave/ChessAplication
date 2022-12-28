package ru.vsu.korotkov.chess.remote;

import javafx.application.Platform;
import ru.vsu.korotkov.chess.console.GameCommand;
import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.parser.ChessParser;
import simplejson.ArrObj;
import simplejson.MapObj;
import simplejson.Obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class OnlineController extends AbstractController {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private GameCommand currCommand;


    public OnlineController(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(this::run).start();
    }

    public OnlineController(int port) throws IOException {
        this(new Socket("localhost", port));
    }

    @Override
    public void startGame() {
        //there's nothing to do
    }

    @Override
    public void notifyClick(Coord[] coord) {
        //todo print command coord
        Obj obj = new MapObj();
        Obj from = new MapObj();
        Obj to = new MapObj();
        from.put("x", "" + coord[0].getX());
        from.put("y", "" + coord[0].getY());
        to.put("x", "" + coord[1].getX());
        to.put("y", "" + coord[1].getY());
        obj.put("from", from);
        obj.put("to", to);
        send(GameCommand.GCOORD, obj.toString());
    }


    @Override
    public void notifyUpdate(MoveResult move) {
        Obj obj = new MapObj();
        Obj from = new MapObj();
        Obj to = new MapObj();

        from.put("x", "" + move.coord1().getX());
        from.put("y", "" + move.coord1().getY());
        to.put("x", "" + move.coord2().getX());
        to.put("y", "" + move.coord2().getY());

        obj.put("movetype", move.moveType().toString());
        obj.put("from", from);
        obj.put("to", to);
        send(GameCommand.GMOVERESULT, obj.toString());
    }

    @Override
    public void sendGameField(Figure[][] figures) {
        Obj res = new MapObj();
        Obj arrObj = new ArrObj();
        for (Figure[] value : figures) {
            for (int j = 0; j < figures[0].length; j++) {
                Figure figure = value[j];
                if (figure == null)
                    continue;
                Obj figureObj = new MapObj();
                figureObj.put("x", String.valueOf(figure.getCoord().getX()));
                figureObj.put("y", String.valueOf(figure.getCoord().getY()));
                figureObj.put("name", figure.toString());

                arrObj.append(figureObj);
            }
        }
        res.put("field", arrObj);
        send(GameCommand.FIELD, arrObj.toString());
    }

    private void send(GameCommand gameCommand, String message) {
        System.out.println("я отправил" + gameCommand.toString());
        out.println(gameCommand + "|" + message);
    }

    private void run() {
        while (true) {
            try {
                String[] arr = in.readLine().split("\\|");
                String command = arr[0];
                if (command.equals(GameCommand.FIELD.toString())) {
                    Figure[][] figures = ChessParser.getField(Arrays.toString(arr));
                    Platform.runLater(() -> clientFieldListeners.forEach(l -> l.setGameField(figures)));
                } else if (command.equals(GameCommand.GCOORD.toString())) {
                    Obj obj = Obj.fromString(arr[1]);
                    Coord[] coords = new Coord[2];
                    coords[0] = new Coord(
                            Integer.parseInt(obj.get("from").get("x").val()),
                            Integer.parseInt(obj.get("from").get("y").val())
                    );
                    coords[1] = new Coord(
                            Integer.parseInt(obj.get("to").get("x").val()),
                            Integer.parseInt(obj.get("to").get("y").val())
                    );

                    gameMoveListeners.forEach(l -> l.makeMove(coords));
                } else if (command.equals(GameCommand.GMOVERESULT.toString())) {
                    Obj obj = Obj.fromString(arr[1]);
                    MoveType moveType = MoveType.valueOf(obj.get("movetype").val());

                    Coord coord1 = new Coord(
                            Integer.parseInt(obj.get("from").get("x").val()),
                            Integer.parseInt(obj.get("from").get("y").val())
                    );
                    Coord coord2 = new Coord(
                            Integer.parseInt(obj.get("to").get("x").val()),
                            Integer.parseInt(obj.get("to").get("y").val())
                    );
                    MoveResult moveResult = new MoveResult(moveType, coord1, coord2);
                    Platform.runLater(() -> clientMoveListeners.forEach(l -> l.setMoveResult(moveResult)));
                } else out.println("error command, try again");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}



