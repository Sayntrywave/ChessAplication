package ru.vsu.korotkov.chess.fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.move.MoveResult;
import ru.vsu.korotkov.chess.players.PlayerType;
import ru.vsu.korotkov.chess.remote.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

//todo подумать над названием
public class GameController implements IGameController {

    ClientSideController controller;

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private final Group tileGroup = new Group();
    private final Group pieceGroup = new Group();


    Figure[][] gameField;

    public AnchorPane root;



    @FXML
    public void initialize() {
//        createContent(new Game());
    }

    private void createContent() throws IOException {
        gameField = controller.getField();
        for (int i = 0; i < gameField.length; i++) {
            System.out.println(Arrays.toString(gameField[i]));
        }
        root.setPrefSize(WIDTH*TILE_SIZE,HEIGHT*TILE_SIZE);
        root.getScene().getWindow().setHeight(WIDTH*TILE_SIZE + 74);
        root.getScene().getWindow().setWidth(WIDTH*TILE_SIZE);
        root.getScene().getWindow().centerOnScreen();

        root.getChildren().addAll(tileGroup,pieceGroup);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[y][x] = tile;

                tileGroup.getChildren().add(tile);
                Figure figure =gameField[y][x];
                if (figure != null){
                    Piece piece = makePiece(figure);
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        System.out.println();
//        changeScene();
    }
    @Override
    public void movePiece(MoveResult move){
        int x0 = move.coord1().getX();
        int y0 = move.coord1().getY();
        int newX = move.coord2().getX();
        int newY = move.coord2().getY();
        Piece piece = board[y0][x0].getPiece();

        switch (move.moveType()) {
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
    }

    private void changeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
    }

    private int toBoard(double pixel) {
        return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
    }
    private Piece makePiece(Figure figure){
        Piece piece = new Piece(figure.getPieceType(),figure.getCoord());
        piece.setOnMouseReleased(e -> {

            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());
            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                return;
            }
            controller.notifyClick(new Coord[]{
                    new Coord(piece.getX(), piece.getY()),
                    new Coord(newX,newY)
            });
        });
        return piece;
    }
    @FXML
    protected void onLocalGameButtonPressed() throws IOException {
        controller = new OfflineController(this);
        createContent();
    }
    @FXML
    protected void onRemoteGameButtonPressed() throws IOException {
//        controller = new OnlineController(this); // надо сделать еще один интерфейс для игрового контроллера
        createContent();
    }
}