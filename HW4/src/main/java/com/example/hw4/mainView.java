package com.example.hw4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * mainView Class
 * This class will start the project
 *
 * @author YuchenZhao yz1116, Jinrui Li jl2340
 */
public class mainView extends Application {

    /**
     * Set the size of the interface and the title.
     *
     * @param stage an interface
     * @throws IOException throw out the I/O exception
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainView.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("RU Pizza");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initialization interface.
     */
    public static void main(String[] args) {
        launch();
    }
}