package ru.vsu.korotkov.chess.remote;

import ru.vsu.korotkov.chess.figures.Figure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RemoteController implements Controller{

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public RemoteController() throws IOException {
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
}
