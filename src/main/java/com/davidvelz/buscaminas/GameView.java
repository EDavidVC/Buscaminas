package com.davidvelz.buscaminas;

import com.davidvelz.buscaminas.Objects.Buttons;
import com.davidvelz.buscaminas.Objects.Cronometer;
import com.davidvelz.buscaminas.Objects.ModeGame;
import com.davidvelz.buscaminas.Objects.Vector;
import com.davidvelz.buscaminas.decoration.numberFormat;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameView{
    //GameProperties
    @FXML
    private Text modeGameText;
    @FXML
    private Text modeGameBombCount;
    @FXML
    private Text modeGameFlagCount;
    @FXML
    private Text modeGameClickCount;
    //Table Propierties
    @FXML
    private ScrollPane tableScroll;
    @FXML
    private GridPane tableGame;
    @FXML
    private GridPane gameView;
    @FXML
    private Text cronometerText;
    private SceneController sceneController;
    private ModeGame modeGame;
    private ArrayList<Vector> positions;

    private int clickCount = 0;
    private Cronometer cronometer;


    public ModeGame getModeGame() {
        return modeGame;
    }
    public void setModeGame(ModeGame modeGame) {
        this.modeGame = modeGame;
    }
    public  void setSceneController(SceneController sceneController){
        this.sceneController = sceneController;
    }
    public SceneController getSceneController(){
        return this.sceneController;
    }
    private void buttonClicked(Buttons button){
        if(!button.getStyleClass().contains("flag")){
            addClick();

            if (this.positions == null){
                generatePositions(button.getX(), button.getY());
            }
            if(button.isBomb()){
                for (Vector position : positions){
                    Buttons buttonTMP = (Buttons) tableGame.lookup("#idx"+position.getX()+"y"+position.getY());

                    buttonTMP.setDisable(true);
                    buttonTMP.activeBomb();
                    buttonTMP.getStyleClass().clear();
                    buttonTMP.getStyleClass().add("bomb");
                    //button.getStyleClass().add("bomb");
                    //System.out.println(" EXPLOTA X: "+ position.getX() + " Y: " + position.getY());
                }
            }else{
                boolean running = true;

                ArrayList<Buttons> emptyButtons = getButtons(button);

                while (running){

                    ArrayList<Buttons> tmpEmptyButtons = new ArrayList<>();
                    //se buscan actuales
                    for (Buttons empty : emptyButtons){
                        ArrayList<Buttons> tmpEmpty = getButtons(empty);
                        if (empty.getBombsDetected() == 0 &&
                                !(empty.isBomb() ||
                                empty.getStyleClass().contains("flag")||
                                empty.getStyleClass().contains("bomb")||
                                empty.getStyleClass().contains("question_pick"))) {
                            for (Buttons test : tmpEmpty){
                                if (!emptyButtons.contains(test)){
                                    tmpEmptyButtons.add(test);
                                }
                            }
                        }

                    }
                    //Añadiendo nuevos botones
                    for (Buttons newButton : tmpEmptyButtons){
                        if(!emptyButtons.contains(newButton)){
                            emptyButtons.add(newButton);

                        }
                    }
                    if (tmpEmptyButtons.size() == 0 ){
                        running = false;
                    }

                }

                for (Buttons empty : emptyButtons){
                    if (empty.getBombsDetected() == 0) {

                    }else{
                        empty.getStyleClass().add("class" + empty.getBombsDetected());
                        empty.setText(""+empty.getBombsDetected());
                    }
                    if (empty.getStyleClass().contains("question_pick")){
                        empty.getStyleClass().remove("question_pick");
                    }
                    empty.setDisable(true);
                }
            }
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

        for(int x = -1; x <= 1 ; x++){
            for(int y = -1; y <= 1;y++){
                try{
                    Buttons emptyButton = (Buttons) tableGame.lookup("#idx"+( buttonX+x )+"y"+( buttonY +y ));
                    if(!emptyButton.isBomb()){
                        emptyButtons.add(emptyButton);
                    }
                }catch (NullPointerException e){
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
            randx = new Random().nextInt(modeGame.getHeight());
            randy = new Random().nextInt(modeGame.getWidth());
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
            if (positions.size() >= this.modeGame.getCantBomb()){
                notFull = false;
            }

        }
        this.positions = positions;
        for (Vector position : positions){
            Buttons button = (Buttons) tableGame.lookup("#idx"+position.getX()+"y"+position.getY());
            button.activeBomb();

            for (int x = -1; x <= 1; x++){
                for (int y = -1; y <= 1; y++){
                    try{
                        Buttons bBomb = (Buttons) tableGame.lookup("#idx"+( position.getX()+x )+"y"+( position.getY() +y ));
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
        addClick();
        if (!button.getStyleClass().contains("flag") && !button.getStyleClass().contains("question_pick")){
            button.getStyleClass().add("flag");
            modeGameFlagCount.setText(""+(numberFormat.reformatNumber(modeGameFlagCount.getText()) - 1 ));
        }else if (button.getStyleClass().contains("flag")){
            button.getStyleClass().remove("flag");
            modeGameFlagCount.setText(""+(numberFormat.reformatNumber(modeGameFlagCount.getText()) + 1 ));
            button.getStyleClass().add("question_pick");
        }else if (button.getStyleClass().contains("question_pick")){
            button.getStyleClass().remove("question_pick");
        }

    }
    private void addClick(){
        if (clickCount == 0) {
            cronometer = new Cronometer(cronometerText);
            cronometer.startCronometer();
        }
        clickCount++;
        modeGameClickCount.setText(new numberFormat(1, clickCount).getFormatedNumber());
    }

    public void startTable(){
        for (int x = 0; x<modeGame.getHeight(); x++){
            for (int y = 0; y<modeGame.getWidth(); y++){
                Buttons tempButton = new Buttons(x, y);
                int sizeButton = 25;
                tempButton.setPrefSize(sizeButton,sizeButton);
                tempButton.setMinSize(sizeButton,sizeButton);
                tempButton.setMaxSize(sizeButton,sizeButton);
                tempButton.setId("idx"+x+"y"+y);
                tempButton.getStyleClass().add("buttonGame");
                tempButton.setFocusTraversable(false);
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
    public void loadgamePropierties(){
        modeGameText.setText(""+this.modeGame.getHeight()+" x "+ this.modeGame.getWidth());
        modeGameBombCount.setText(""+modeGame.getCantBomb());
        modeGameFlagCount.setText(""+modeGame.getCantBomb());
        modeGameClickCount.setText(""+clickCount);
        System.out.println(""+tableGame.getProperties());
    }
}
