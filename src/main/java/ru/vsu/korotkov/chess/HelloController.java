package ru.vsu.korotkov.chess;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.vsu.korotkov.chess.userInteractions.UI.Piece;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}