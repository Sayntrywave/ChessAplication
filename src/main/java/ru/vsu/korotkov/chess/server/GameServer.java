package ru.vsu.korotkov.chess.server;

import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.players.Human;
import ru.vsu.korotkov.chess.players.Player;
import ru.vsu.korotkov.chess.players.PlayerType;
import ru.vsu.korotkov.chess.remote.OnlineController;
import ru.vsu.korotkov.chess.remote.ServerSideController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer(9999);
        server.start();
    }

    private final int port;

    private Game game;

    public GameServer(int port) throws IOException {

        this.port = port;
        game = new Game();

    }

    public void start() throws IOException {
        System.out.printf("Server started on: %d%n", port);
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket1 = serverSocket.accept();

        game.setPlayer(PlayerType.HUMAN,true,new OnlineController(socket1));

        Socket socket2 = serverSocket.accept();
        game.setPlayer(PlayerType.HUMAN,false,new OnlineController(socket2));
        game.start();


    }


}
