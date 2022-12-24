package ru.vsu.korotkov.chess.fx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.events.RoundEventListeners;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.model.Game;
import ru.vsu.korotkov.chess.model.LocalGame;
import ru.vsu.korotkov.chess.model.NetworkGame;
import ru.vsu.korotkov.chess.players.PlayerType;

import java.io.IOException;
import java.util.Objects;
//todo подумать над названием
public class GameController {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    Game game;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private final Group tileGroup = new Group();
    private final Group pieceGroup = new Group();


    Figure[][] gameField;

    public AnchorPane root;



    @FXML
    public void initialize() {
//        createContent(new Game());
    }

    private void setGame(Game game){
        this.game = game;
        gameField = game.getGameField();

        game.addGameOverListeners(Platform::exit);
    }
    private void createContent(Game game) throws IOException {
        setGame(game);

        root.setPrefSize(WIDTH*TILE_SIZE,HEIGHT*TILE_SIZE);
        root.getScene().getWindow().setHeight(WIDTH*TILE_SIZE + 74);
        root.getScene().getWindow().setWidth(WIDTH*TILE_SIZE);
        root.getScene().getWindow().centerOnScreen();

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
//        changeScene();
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
    @FXML
    protected void onLocalGameButtonPressed() throws IOException {

        createContent(new LocalGame(PlayerType.HUMAN,PlayerType.HUMAN));
    }
    @FXML
    protected void onRemoteGameButtonPressed() throws IOException {
        createContent(new NetworkGame(PlayerType.HUMAN,"localhost",9999));
    }
}