package ru.vsu.korotkov.chess.server;

import ru.vsu.korotkov.chess.model.Game;
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
    ServerSideController controller;
    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer(9999);
        server.start();
    }

    private final int port;

    private Game game;

    public GameServer(int port) throws IOException {

        this.port = port;
//        ServerSocket serverSocket = new ServerSocket(port);

        controller = new OnlineController(port);

    }

    public void start() throws IOException {
        System.out.printf("Server started on: %d%n", port);
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        System.out.printf("Client connected from: %s%n", socket.getInetAddress());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        out.println("You're connected. Waiting for your opponent");
        socket = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        out.println("You're connected. The game has started");
        game.start();

//        game = new LocalGame(PlayerType.HUMAN,PlayerType.HUMAN);

     /*   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
        String command;
        boolean gameOver = true;
        while (gameOver) {
            if ((command = in.readLine())!= null){
                System.out.println(command);
            }
            assert command != null;
            //if command = field then game.getField()
            if (command.equalsIgnoreCase("END")){
                gameOver = false;
                socket.close();
            }
            //TODO create client session and start it
//            ClientSession session = new ClientSession(socket);
//            new Thread(session).start();
        }*/
    }


}
