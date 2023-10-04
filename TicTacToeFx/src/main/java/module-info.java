module com.example.tictactoefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoefx to javafx.fxml;
    exports com.example.tictactoefx;
}