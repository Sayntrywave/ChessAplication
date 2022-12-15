package ru.vsu.korotkov.chess.userInteractions.UI;

import javafx.scene.layout.StackPane;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;

public class Piece extends StackPane {
    PieceType figure;
    private Coord coord;

    public PieceType getFigure() {
        return figure;
    }

    public int getX() {
        return coord.getX();
    }
    public int getY() {
        return coord.getY();
    }

    public Piece(PieceType pieceType, Coord coord) {
//        setOnMouseClicked(); мы устанавливаем, что должна делать программа, когда нажата мышка
    }


}
