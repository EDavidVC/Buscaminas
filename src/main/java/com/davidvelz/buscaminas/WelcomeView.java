package com.davidvelz.buscaminas;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class WelcomeView {
    private SceneController sceneController;
    public  void setSceneController(SceneController sceneController){
        this.sceneController = sceneController;
    }
    public SceneController getSceneController(){
        return this.sceneController;
    }
    public void startGame(MouseEvent mouseEvent) throws IOException {
        sceneController.activeGameView(10);


    }
}
