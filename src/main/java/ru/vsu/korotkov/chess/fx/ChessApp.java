package ru.vsu.korotkov.chess.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.events.GameOverListener;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.model.Game;

import java.io.IOException;

public class ChessApp extends Application  {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    Game game;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private final Group tileGroup = new Group();
    private final Group pieceGroup = new Group();


    Figure[][] gameField;

    private void createGame(){
        game = new Game();
        gameField = game.getGameField();
        game.addGameOverListeners(new GameOverListener() {
            @Override
            public void onGameOver() {
                Platform.exit();
            }
        });
    }
    private Parent createContent() {
        createGame();

        Pane root = new Pane();
        root.setPrefSize(WIDTH*TILE_SIZE,HEIGHT*TILE_SIZE);
        root.getChildren().addAll(tileGroup,pieceGroup);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);
                Figure figure =gameField[y][x];
                if (figure != null){
                    Piece piece = makePiece(figure);
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
                }
            }
        return root;
    }
    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }
    private Piece makePiece(Figure figure){
        Piece piece = new Piece(figure.getPieceType(),figure.getCoord());
        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveType result;

            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = MoveType.NONE;
            } else {
                result = game.makeMove(new Coord[]{piece.getCoord(), new Coord(newX, newY)});
            }

            int x0 = piece.getX();
            int y0 = piece.getY();

            switch (result) {
                case NONE -> piece.abortMove();
                case NORMAL -> {
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                }
                case KILL -> {
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    Piece otherPiece = board[newX][newY].getPiece();
                    board[otherPiece.getX()][otherPiece.getY()].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                }
            }
        });
        return piece;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }

}