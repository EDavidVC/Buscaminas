package com.davidvelz.buscaminas;

import com.davidvelz.buscaminas.Objects.Buttons;
import com.davidvelz.buscaminas.Objects.Vector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameView{
    @FXML
    private GridPane tableGame;
    @FXML
    private AnchorPane gameView;
    private SceneController sceneController;
    private int modeGame;
    private ArrayList<Vector> positions;

    public int getModeGame() {
        return modeGame;
    }
    public void setModeGame(int modeGame) {
        this.modeGame = modeGame;
    }
    public  void setSceneController(SceneController sceneController){
        this.sceneController = sceneController;
    }
    public SceneController getSceneController(){
        return this.sceneController;
    }
    private void buttonClicked(Buttons button){
        if (this.positions == null){
            generatePositions(button.getX(), button.getY());
        }
        if(button.getStyleClass().contains("flag")){
            button.getStyleClass().remove("flag");
        }
        if(button.isBomb()){
            for (Vector position : positions){
                Buttons buttonTMP = (Buttons) tableGame.lookup("#id"+position.getX()+position.getY());
                buttonTMP.getStyleClass().add("bomb");
                buttonTMP.setDisable(true);
                buttonTMP.activeBomb();
                //button.getStyleClass().add("bomb");
                //System.out.println(" EXPLOTA X: "+ position.getX() + " Y: " + position.getY());
            }
        }else{
            button.setText(""+button.getBombsDetected());
            //ArrayList<Vector> emtyPositions =  pressButton(button);
            //for (Vector vector : emtyPositions){
            //    Buttons emtyButton = (Buttons) tableGame.lookup("#id"+vector.getX()+vector.getY());
            //    emtyButton.getStyleClass().add("flag");
            //}
            /*for (Buttons emptyButton : getButtons(button)){
                if (!emptyButton.isBomb()){
                    emptyButton.setText(""+emptyButton.getBombsDetected());
                }

            }*/

        }
    }
    private ArrayList<Buttons> getButtons(Buttons buttonPressed) throws NullPointerException {
        ArrayList<Buttons> emptyButtons;
        emptyButtons = new ArrayList<>();
        // Vertical
        int buttonX;
        int buttonY;

        buttonX = buttonPressed.getX();
        buttonY = buttonPressed.getY();
        emptyButtons.add(buttonPressed);

        //up, down , left, right
        boolean upStop = false;
        boolean downStop = false;
        boolean leftStop = false;
        boolean rightStop = false;


        for(int x = 0; x < modeGame; x++){


            for(int y = 0; y < modeGame;y++){

                //up
                Buttons upButton = (Buttons) tableGame.lookup("#id" + (buttonX - x) + (buttonY + y));
                if (upButton != null && !upStop && upButton.getBombsDetected() == 0 && !emptyButtons.contains(upButton)){
                    emptyButtons.add(upButton);
                } else if (upButton != null && !upStop && upButton.getBombsDetected() > 0 && !emptyButtons.contains(upButton)) {
                    emptyButtons.add(upButton);
                    upStop = true;
                }

                //down
                Buttons downButton = (Buttons) tableGame.lookup("#id" + (buttonX + x) + (buttonY + y));
                if ( downButton != null && !downStop &&downButton.getBombsDetected() == 0 && !emptyButtons.contains(downButton)) {
                    emptyButtons.add(downButton);
                } else if (downButton != null && !downStop && downButton.getBombsDetected() > 0 && !emptyButtons.contains(downButton)) {
                    emptyButtons.add(downButton);
                    downStop = true;
                }
                //left
                Buttons leftButton = (Buttons) tableGame.lookup("#id" + (buttonX - x) + (buttonY - y));
                if (leftButton != null && !leftStop &&leftButton.getBombsDetected() == 0 && !emptyButtons.contains(leftButton)) {
                    emptyButtons.add(leftButton);
                } else if (leftButton != null && !leftStop && leftButton.getBombsDetected() > 0 && !emptyButtons.contains(leftButton)) {
                    emptyButtons.add(leftButton);
                    leftStop = true;
                }
                //right
                Buttons rightButton = (Buttons) tableGame.lookup("#id" + (buttonX + x) + (buttonY - y));
                if (rightButton != null && !rightStop && rightButton.getBombsDetected() == 0 && !emptyButtons.contains(rightButton)) {
                    emptyButtons.add(rightButton);
                } else if (rightButton != null && !rightStop && rightButton.getBombsDetected() > 0 && !emptyButtons.contains(rightButton)) {
                    emptyButtons.add(rightButton);
                    rightStop = true;
                }
            }
        }
        return emptyButtons;
    }
    private void generatePositions(int eX, int eY){
        ArrayList<Vector> positions = new ArrayList<>();
        boolean notFull = true;
        while (notFull){
            int randx;
            int randy;
            randx = new Random().nextInt(modeGame);
            randy = new Random().nextInt(modeGame);
            boolean exist = false;
            for (Vector position : positions) {
                if (position.getX() == randx && position.getY() == randy ){
                    exist = true;
                }else if (randx == eX && randy == eY){
                    exist = true;
                }
            }
            if (!exist){
                positions.add(new Vector(randx, randy));
            }
            if (positions.size() >= modeGame * 2){
                notFull = false;
            }

        }
        this.positions = positions;
        for (Vector position : positions){
            Buttons button = (Buttons) tableGame.lookup("#id"+position.getX()+position.getY());
            button.activeBomb();

            for (int x = -1; x <= 1; x++){
                for (int y = -1; y <= 1; y++){
                    try{
                        Buttons bBomb = (Buttons) tableGame.lookup("#id"+( position.getX()+x )+( position.getY() +y ));
                        if(!bBomb.getStyleClass().contains("flag")){
                            bBomb.bombDetected();
                        }
                    }catch (NullPointerException e){
                    }


                }
            }
        }
    }
    private void buttonSecondaryClicked(Buttons button){
        button.getStyleClass().add("flag");
    }

    public void startTable(){
        for (int x = 0; x<modeGame; x++){
            for (int y = 0; y<modeGame; y++){
                Buttons tempButton = new Buttons(x, y);
                tempButton.setId("id"+x+y);
                tempButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY){
                            buttonClicked(tempButton);
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            buttonSecondaryClicked(tempButton);
                        }
                    }
                });
                tableGame.add(tempButton, x, y);
            }
        }
    }
}
