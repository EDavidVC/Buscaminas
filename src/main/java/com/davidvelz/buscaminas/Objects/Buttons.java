package com.davidvelz.buscaminas.Objects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.controlsfx.control.action.Action;

public class Buttons extends Button {
    //Position
    private int x;
    private int y;

    private int size;
    private boolean bomb;
    private boolean pressed ;
    private int bombsDetected;

    public Buttons() {
        this.x = 0;
        this.y = 0;
        this.size = 30;
        pressed = false;
        bombsDetected = 0;
        initSize();
    }

    public Buttons(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 30;
        pressed = false;
        bombsDetected = 0;
        initSize();
    }

    public Buttons(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        pressed = false;
        bombsDetected = 0;
        initSize();
    }
    public Buttons(int x, int y, int size, boolean bomb) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.bomb = bomb;
        pressed = false;
        bombsDetected = 0;
        initSize();
    }
    public Buttons(int x, int y, boolean bomb) {
        this.x = x;
        this.y = y;
        this.bomb = bomb;
        pressed = false;
        bombsDetected = 0;
        initSize();
    }


    private void initSize(){
        setMaxSize(size, size);
        setMinSize(size, size);
        setPrefSize(size, size);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBomb(){
        return bomb;
    }
    public void pressed(){
        this.pressed = true;
    }
    public void activeBomb(){
        bomb = true;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public int getBombsDetected() {
        return bombsDetected;
    }

    public void setBombsDetected(int bombsDetected) {
        this.bombsDetected = bombsDetected;
    }
    public void bombDetected(){
        this.bombsDetected++;
    }
}
