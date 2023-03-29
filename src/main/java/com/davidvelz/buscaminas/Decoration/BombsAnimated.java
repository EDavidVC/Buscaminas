package com.davidvelz.buscaminas.Decoration;

import com.davidvelz.buscaminas.Objects.Buttons;
import com.davidvelz.buscaminas.Objects.Vector;
import javafx.animation.ScaleTransition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.controlsfx.control.spreadsheet.Filter;
import org.controlsfx.control.spreadsheet.Grid;
import java.io.File;
import java.util.ArrayList;
public class BombsAnimated extends Thread{

    private ArrayList<Vector> positions;
    private GridPane tableGame;
    private Buttons initButton;
    public BombsAnimated(Buttons initButton, ArrayList<Vector> positions, GridPane tableGame){
        this.positions = positions;
        this.tableGame = tableGame;
        this.initButton = initButton;

    }

    public void run(){

        for (Vector position : positions){
            Buttons buttonTMP = (Buttons) tableGame.lookup("#idx"+position.getX()+"y"+position.getY());



            try {
                sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            buttonTMP.setDisable(true);
            buttonTMP.activeBomb();
            buttonTMP.getStyleClass().clear();
            buttonTMP.getStyleClass().add("bomb");
            File sonido = new File("sound/");


            //button.getStyleClass().add("bomb");
            //System.out.println(" EXPLOTA X: "+ position.getX() + " Y: " + position.getY());
        }
    }
    public void startAnimation(){
        this.start();
    }
}
