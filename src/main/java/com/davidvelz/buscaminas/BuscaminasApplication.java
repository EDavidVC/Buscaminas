package com.davidvelz.buscaminas;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class BuscaminasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(true);
        stage.setMinWidth(850);
        stage.setMinHeight(600);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                        System.exit(0);
                    }
                });


                SceneController sceneController;
        sceneController = new SceneController(stage);


    }

    public static void main(String[] args) {
        launch();
    }
}