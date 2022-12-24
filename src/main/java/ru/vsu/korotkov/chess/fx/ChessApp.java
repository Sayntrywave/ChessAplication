package ru.vsu.korotkov.chess.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.vsu.korotkov.chess.enums.MoveType;
import ru.vsu.korotkov.chess.events.GameOverListener;
import ru.vsu.korotkov.chess.figures.Coord;
import ru.vsu.korotkov.chess.figures.Figure;
import ru.vsu.korotkov.chess.model.Game;

import java.io.IOException;

public class ChessApp extends Application  {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChessApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }

}