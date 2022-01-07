module com.example.hw4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;
    requires junit;


    opens com.example.hw4 to javafx.fxml;
    exports com.example.hw4;
}