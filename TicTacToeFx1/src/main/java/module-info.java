module tictactoe.tictactoefx1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens tictactoe.tictactoefx1 to javafx.fxml;
    exports tictactoe.tictactoefx1;
}