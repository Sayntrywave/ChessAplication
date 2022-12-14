package ru.vsu.korotkov.chess.userInteractions.UI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.vsu.korotkov.chess.UserInteraction;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;

import java.io.IOException;

public class HelloApplication extends Application implements UserInteraction {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    Pane root;
    Figure[][] gameField;




    @Override
    public void updateGameField() {

    }

    @Override
    public Coord[] getMove() {
        return new Coord[0];
    }

    @Override
    public void printWinner(int numberOfMoves) {

    }

    @Override
    public void setGameField(Figure[][] gameField) {
        this.gameField = gameField;
        createContent();
    }
    //setField где мы добавим в сцену элементы используя createContent()
    // scene.getRoot()

    private Parent createContent() {
        root.setPrefSize(WIDTH*TILE_SIZE,HEIGHT*TILE_SIZE);
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        root = new Pane();
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}