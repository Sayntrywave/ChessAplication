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

public class OnlineController implements ClientSideController,ServerSideController {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;



    public OnlineController() throws IOException {
        socket = new Socket("localhost",9999);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);


    }

    @Override
    public Figure[][] getField() {
        //todo get field
        out.println("getField");
        // get info from in.readLine();
        return new Figure[0][];
    }

    @Override
    public void notifyClick(Coord[] coord) {
        // in.print(coord)
    }

    @Override
    public Coord[] askClient() {
        // get info from in.readLine();
        return new Coord[0];
    }

    @Override
    public void notifyUpdate(MoveResult move) {
        // in.print
    }
}
