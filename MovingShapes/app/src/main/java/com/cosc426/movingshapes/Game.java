package com.cosc426.movingshapes;

public class Game {

    private Shape[] shapes = new Shape[20];
    private int width, height;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        for(int i = 0; i < 20; i++) {
            shapes[i] = new Shape(width, height);
        }
    }

    public void move() {
        for(int i = 0; i < shapes.length; i++) {
            shapes[i].move();
        }
    }

    public Shape[] getArray() {
        return shapes;
    }
}
