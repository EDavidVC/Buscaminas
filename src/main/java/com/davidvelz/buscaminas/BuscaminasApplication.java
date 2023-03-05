package com.davidvelz.buscaminas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BuscaminasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);


        SceneController sceneController;
        sceneController = new SceneController(stage);


    }

    public static void main(String[] args) {
        launch();
    }
}