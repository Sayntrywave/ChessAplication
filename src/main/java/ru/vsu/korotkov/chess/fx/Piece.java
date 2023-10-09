package ru.vsu.korotkov.chess.fx;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import ru.vsu.korotkov.chess.enums.PieceType;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.fx.graphics.Sprite;

import static ru.vsu.korotkov.chess.fx.GameController.TILE_SIZE;

public class Piece extends StackPane {
    PieceType figure;
    private double mouseX;
    private double mouseY;
    private Coord coord;

    public Piece(PieceType pieceType, Coord coord) {
        this.coord = coord;
        Shape shapeOfPiece = Sprite.getShape(pieceType);
        move(coord.getX(), coord.getY());
        getChildren().add(shapeOfPiece);
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + coord.getX() * TILE_SIZE, e.getSceneY() - mouseY + coord.getY() * TILE_SIZE);
        });
    }

    public PieceType getFigure() {
        return figure;
    }

    public int getX() {
        return coord.getX();
    }

    public int getY() {
        return coord.getY();
    }

    public Coord getCoord() {
        return coord;
    }

    public void move(int x, int y) {
        relocate(x * TILE_SIZE, y * TILE_SIZE);
        coord.setX(x);
        coord.setY(y);
    }


    public void abortMove() {
        move(getX(), getY());
    }
}
