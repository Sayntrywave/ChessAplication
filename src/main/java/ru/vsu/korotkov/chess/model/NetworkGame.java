package ru.vsu.korotkov.chess.model;

import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.players.PlayerType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class NetworkGame extends Game{

    Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public NetworkGame(PlayerType player, String server, int port) {
        super(player);

        try {
            socket = new Socket(server, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            //TODO Handle exception properly
            throw new RuntimeException(e);
        }
    }
//    @Override
//    protected MoveType doOnEndRound(Coord[] coord) {
//            MoveType moveType = makeMove(coord);
//        out.println(coord);
//        System.out.println(coord);
//       /* String response;
//        try {
//            while ((response = in.readLine()) == null);
//            System.out.println(response);
//            String[] split = response.split(":");
//            RoundEvent event = new RoundEvent(
//                    RoundResult.valueOf(split[0]),
//                    Figure.valueOf(split[1]),
//                    Figure.valueOf(split[2]));
//            return event;
//        } catch (IOException ex) {
//            //TODO handle exception
//            throw new RuntimeException(ex);
//        }*/
//    }

    @Override
    public void onRoundFinished(MoveType moveType,Coord[] coords) {
        out.println(Arrays.toString(coords));
        System.out.println(Arrays.toString(coords));
    }
}
