module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vsu.korotkov.chess to javafx.fxml;
    exports ru.vsu.korotkov.chess;
/*    exports ru.vsu.korotkov.chess.pieces;
    opens ru.vsu.korotkov.chess.pieces to javafx.fxml;*/
    exports ru.vsu.korotkov.chess.userInteractions.UI;
    opens ru.vsu.korotkov.chess.userInteractions.UI to javafx.fxml;
}