package com.davidvelz.buscaminas;

import com.davidvelz.buscaminas.Objects.ModeGame;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WelcomeView {
    private SceneController sceneController;
    @FXML
    private GridPane main_content_pane;

    @FXML
    private Button bottonModeOne;
    @FXML
    private Button bottonModeTwo;
    @FXML
    private Button bottonModeThree;
    @FXML
    private TextField customVal;
    @FXML
    private CheckBox customCheckBox;
    @FXML
    private GridPane customTable;

    @FXML
    private TextField customWidth;
    @FXML
    private TextField customHeight;
    @FXML
    private TextField customBomb;


    private ModeGame modeGame;
    public  void setSceneController(SceneController sceneController){
        this.sceneController = sceneController;
    }
    public SceneController getSceneController(){
        return this.sceneController;
    }
    public void startGame(MouseEvent mouseEvent) throws IOException {
        if (customCheckBox.isSelected()){
            ModeGame tmpModeGame = new ModeGame();
            int width = tmpModeGame.getWidth();
            int height = tmpModeGame.getHeight();
            int bomb = tmpModeGame.getCantBomb();
            if(!customWidth.getText().isEmpty()){
                width =Integer.parseInt(customWidth.getText());

            }
            if (!customHeight.getText().isEmpty()){
                height =Integer.parseInt(customHeight.getText());

            }
            if (!customBomb.getText().isEmpty()){
                bomb =Integer.parseInt(customBomb.getText());
            }

            sceneController.activeGameView(new ModeGame(width, height, bomb));
        }else{
            if (modeGame == null){
                System.out.println("SELECCIONE UN MODO");
            }else {
                sceneController.activeGameView(this.modeGame);
            }

        }



    }
    private void setModeGame(ModeGame modeGame){
        this.modeGame = modeGame;
    }

    public void setModeOne(MouseEvent mouseEvent) {
        bottonModeOne.getStyleClass().add("selectedMode");
        bottonModeOne.getStyleClass().remove("noSelectedMode");
        if (bottonModeTwo.getStyleClass().contains("selectedMode")){
            bottonModeTwo.getStyleClass().remove("selectedMode");
        }
        if (!bottonModeTwo.getStyleClass().contains("noSelectedMode")){
            bottonModeTwo.getStyleClass().add("noSelectedMode");
        }
        if (bottonModeThree.getStyleClass().contains("selectedMode")){
            bottonModeThree.getStyleClass().remove("selectedMode");
        }
        if (!bottonModeThree.getStyleClass().contains("noSelectedMode")){
            bottonModeThree.getStyleClass().add("noSelectedMode");
        }
        setModeGame(new ModeGame(8, 8, 10));
    }

    public void setModeTwo(MouseEvent mouseEvent) {
        bottonModeTwo.getStyleClass().add("selectedMode");
        bottonModeTwo.getStyleClass().remove("noSelectedMode");
        if (bottonModeOne.getStyleClass().contains("selectedMode")){
            bottonModeOne.getStyleClass().remove("selectedMode");
        }
        if (!bottonModeOne.getStyleClass().contains("noSelectedMode")){
            bottonModeOne.getStyleClass().add("noSelectedMode");
        }
        if (bottonModeThree.getStyleClass().contains("selectedMode")){
            bottonModeThree.getStyleClass().remove("selectedMode");
        }if (!bottonModeThree.getStyleClass().contains("noSelectedMode")){
            bottonModeThree.getStyleClass().add("noSelectedMode");
        }

        setModeGame(new ModeGame(16, 16, 40));
    }

    public void setModeThree(MouseEvent mouseEvent) {
        bottonModeThree.getStyleClass().add("selectedMode");
        bottonModeThree.getStyleClass().remove("noSelectedMode");
        if (bottonModeOne.getStyleClass().contains("selectedMode")){
            bottonModeOne.getStyleClass().remove("selectedMode");
        }
        if (!bottonModeOne.getStyleClass().contains("noSelectedMode")){
            bottonModeOne.getStyleClass().add("noSelectedMode");
        }
        if (bottonModeTwo.getStyleClass().contains("selectedMode")){
            bottonModeTwo.getStyleClass().remove("selectedMode");
        }
        if (!bottonModeTwo.getStyleClass().contains("noSelectedMode")){
            bottonModeTwo.getStyleClass().add("noSelectedMode");
        }
        setModeGame(new ModeGame(16, 30, 99));
    }

    public void customAction(MouseEvent mouseEvent) throws IOException {
        if (bottonModeOne.getStyleClass().contains("selectedMode")){
            bottonModeOne.getStyleClass().remove("selectedMode");
        }
        if (!bottonModeOne.getStyleClass().contains("noSelectedMode")){
            bottonModeOne.getStyleClass().add("noSelectedMode");
        }
        if (bottonModeTwo.getStyleClass().contains("selectedMode")){
            bottonModeTwo.getStyleClass().remove("selectedMode");
        }
        if (!bottonModeTwo.getStyleClass().contains("noSelectedMode")){
            bottonModeTwo.getStyleClass().add("noSelectedMode");
        }
        if (bottonModeThree.getStyleClass().contains("selectedMode")){
            bottonModeThree.getStyleClass().remove("selectedMode");
        }
        if (!bottonModeThree.getStyleClass().contains("noSelectedMode")){
            bottonModeThree.getStyleClass().add("noSelectedMode");
        }

        if(customCheckBox.isSelected()){
            ScaleTransition st = new ScaleTransition(Duration.millis(500), customTable);
            st.setByX(0);
            st.setByY(1);
            st.setAutoReverse(true);


            st.play();



            //slideCustomTable(-0.001);

        }else {
            ScaleTransition st = new ScaleTransition(Duration.millis(500), customTable);
            st.setByX(0);
            st.setByY(-1);
            st.setAutoReverse(true);

            st.play();
        }
    }
}
