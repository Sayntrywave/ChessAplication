package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.fx.Piece;
import ru.vsu.korotkov.chess.move.MoveResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class OnlineController implements ClientSideController,ServerSideController {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;



    public OnlineController(int port) throws IOException {
        socket = new Socket("localhost",port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
    }



    @Override
    public Figure[][] getField() {
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
        return new Figure[0][];
    }

    @Override
    public MoveResult getUpdate() {
        return null;
    }

    @Override
    public void notifyClick(Coord[] coord) {
        //todo print command coord
        out.println(Arrays.toString(coord));
    }

    @Override
    public Coord[] askClient() {
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
        return new Coord[0];
    }

    @Override
    public void notifyUpdate(MoveResult move) {
        //todo command notifyUpdate
        out.println(move);
    }
}
