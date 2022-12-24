package ru.vsu.korotkov.chess.fx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static ru.vsu.korotkov.chess.fx.GameController.TILE_SIZE;

public class Tile extends Rectangle {

    private Piece piece;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(boolean light, int x, int y) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);

        relocate(x * TILE_SIZE, y * TILE_SIZE);

        setFill(light ? Color.valueOf("#d18b47") : Color.valueOf("#b0b0b0"));
    }
}