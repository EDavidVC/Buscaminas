package com.davidvelz.buscaminas.Objects;

public class ModeGame {
    private int width;
    private int height;
    private int cantBomb;

    public ModeGame(int width, int height, int cantBomb) {
        this.width = width;
        this.height = height;
        this.cantBomb = cantBomb;
    }
    public ModeGame(){
        this.width = 8;
        this.height = 8;
        this.cantBomb = 10;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCantBomb() {
        return cantBomb;
    }

    public void setCantBomb(int cantBomb) {
        this.cantBomb = cantBomb;
    }
}
