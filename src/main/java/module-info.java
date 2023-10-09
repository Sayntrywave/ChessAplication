module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vsu.korotkov.chess to javafx.fxml;
    exports ru.vsu.korotkov.chess;
/*    exports ru.vsu.korotkov.chess.pieces;
    opens ru.vsu.korotkov.chess.pieces to javafx.fxml;*/
    exports ru.vsu.korotkov.chess.fx;
    opens ru.vsu.korotkov.chess.fx to javafx.fxml;
    exports ru.vsu.korotkov.chess.events;
    opens ru.vsu.korotkov.chess.events to javafx.fxml;
    exports ru.vsu.korotkov.chess.enums;
    opens ru.vsu.korotkov.chess.enums to javafx.fxml;
    exports ru.vsu.korotkov.chess.console;
    opens ru.vsu.korotkov.chess.console to javafx.fxml;
    exports ru.vsu.korotkov.chess.model;
    opens ru.vsu.korotkov.chess.model to javafx.fxml;
}