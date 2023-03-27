package com.davidvelz.buscaminas.decoration;

import javafx.scene.control.TextField;

public class Slide {
    private TextField textField;
    private int nanoReference;
    private int FPS;

    private double NANO_FPS;

    public Slide(){
        this.nanoReference = 1000000000;
        this.FPS  = 40;
        this.NANO_FPS = nanoReference / FPS;
    }
    public Slide(TextField textField){
        this.nanoReference = 1000000000;
        this.FPS  = 40;
        this.NANO_FPS = nanoReference / FPS;
        this.textField = textField;
    }
    public Slide(TextField textField, int timeSeconds){
        this.nanoReference = 1000000000 * nanoReference;
        this.FPS  = 40;
        this.NANO_FPS = nanoReference / FPS;
        this.textField = textField;
    }
    public void SlideStart(){
        long nanoTime = System.nanoTime();

        double time;
        double delta = 0;
        boolean isRunning = false;
        while(isRunning){
            long initTime = System.nanoTime();
            time = initTime - nanoTime;
            nanoTime = initTime;

            delta += time/NANO_FPS;

            if(delta >= 1){
                delta -= 1;
            }
        }
    }
}
