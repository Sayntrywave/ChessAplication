package ru.vsu.korotkov.chess.userInteractions.UI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        setWidth(ChessApp.TILE_SIZE);
        setHeight(ChessApp.TILE_SIZE);

        relocate(x * ChessApp.TILE_SIZE, y * ChessApp.TILE_SIZE);

        setFill(light ? Color.valueOf("#d18b47") : Color.valueOf("#b0b0b0"));
    }
}