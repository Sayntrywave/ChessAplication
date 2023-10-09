package ru.vsu.korotkov.chess.fx.graphics;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import ru.vsu.korotkov.chess.enums.PieceType;

import static ru.vsu.korotkov.chess.fx.GameController.TILE_SIZE;

public class Sprite {
    public static Shape getShape(PieceType pieceType) {
        Shape shape = switch (pieceType.getIndex()) {
            case 0 -> new Ellipse(TILE_SIZE * 0.5, TILE_SIZE * 0.5);
            case 1 -> new Rectangle(TILE_SIZE * 0.5, TILE_SIZE * 0.5);
            default -> new Ellipse(TILE_SIZE * 0.3, TILE_SIZE * 0.3);
        };
        if (pieceType.isWhite()) {
            shape.setFill(Color.WHITE);
        } else shape.setFill(Color.BLACK);
        return shape;
    }
}
