package com.davidvelz.buscaminas;

import com.davidvelz.buscaminas.Objects.ModeGame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("main_view_style.css").toExternalForm());

        WelcomeView welcomeView = fxmlLoader.getController();
        welcomeView.setSceneController(this);

        startStage(scene, "Biembenido");

    }
    public void activeGameView(ModeGame modeGame) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(BuscaminasApplication.class.getResource("game_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("game_view_style.css").toExternalForm());

        GridPane gameGridView = (GridPane) scene.lookup("#gameView");
        gameGridView.setMinHeight(this.principalStage.getHeight());
        gameGridView.setMinWidth(this.principalStage.getWidth());
        GameView gameView = fxmlLoader.getController();
        gameView.setSceneController(this);
        gameView.setModeGame(modeGame);
        gameView.startTable();

        startStage(scene, "Buscaminas");

    }
    private void startStage(Scene scene, String title){
        principalStage.setTitle(title);
        principalStage.setScene(scene);
        principalStage.show();
    }
}
