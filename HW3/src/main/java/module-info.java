module com.example.hw3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hw3 to javafx.fxml;
    exports com.example.hw3;
}