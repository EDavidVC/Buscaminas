package com.davidvelz.buscaminas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage principalStage;
    public SceneController(Stage stage) throws IOException {
        this.principalStage = stage;
        activeWelcomeView();
    }
    public void activeWelcomeView() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(BuscaminasApplication.class.getResource("welcome_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("main_view_style.css").toExternalForm());

        WelcomeView welcomeView = fxmlLoader.getController();
        welcomeView.setSceneController(this);

        principalStage.setTitle("BIEMBENIDO");
        principalStage.setMaxHeight(800);
        principalStage.setMaxWidth(600);
        principalStage.setScene(scene);
        principalStage.show();

    }
    public void activeGameView(int modeGame) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(BuscaminasApplication.class.getResource("game_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("game_view_style.css").toExternalForm());

        GameView gameView = fxmlLoader.getController();
        gameView.setSceneController(this);
        gameView.setModeGame(modeGame);
        gameView.startTable();

        principalStage.setTitle("BUSCAMINAS");
        principalStage.setMaxHeight(800);
        principalStage.setMaxWidth(600);
        principalStage.setScene(scene);
        principalStage.show();

    }
}
