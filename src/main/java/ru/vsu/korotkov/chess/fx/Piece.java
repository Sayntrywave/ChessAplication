package ru.vsu.korotkov.chess.fx;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import ru.vsu.korotkov.chess.enums.PieceType;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.fx.graphics.Sprite;

import static ru.vsu.korotkov.chess.fx.ChessApp.TILE_SIZE;

public class Piece extends StackPane {
    private double mouseX;
    private double mouseY;
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

    public Coord getCoord() {
        return coord;
    }

    public Piece(PieceType pieceType, Coord coord) {
//        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("src/main/resources/ru/vsu/korotkov/chess/Chess_kdt45.svg")));
////        ImageView img = new ImageView();
//        img.setImage(image);
        this.coord = coord;
        Shape shapeOfPiece = Sprite.getShape(pieceType);
        move(coord.getX(),coord.getY());
//        rectangle.setTranslateX();
        getChildren().add(shapeOfPiece);
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + coord.getX()*TILE_SIZE, e.getSceneY() - mouseY + coord.getY()*TILE_SIZE);
        });
//        setOnMouseClicked(); мы устанавливаем, что должна делать программа, когда нажата мышка
    }
    public void move(int x,int y){
        relocate(x*TILE_SIZE,y*TILE_SIZE);
    }


    public void abortMove() {
        move(getX(),getY());
    }
}
