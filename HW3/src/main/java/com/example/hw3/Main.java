package com.example.hw3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class.
 * This class will start the project.
 *
 * @author yuchenzhao yz1116, Jinrui Li jl2340
 */
public class Main extends Application {

    /**
     * Set the size and the title.
     *
     * @param stage an interface
     * @throws IOException throw out the I/O exception
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Tuition-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 510);
        stage.setTitle("Tuition Manager");
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