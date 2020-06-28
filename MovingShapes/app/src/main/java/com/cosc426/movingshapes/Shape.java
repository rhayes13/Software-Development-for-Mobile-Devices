package com.cosc426.movingshapes;

import android.graphics.Color;

import java.util.Random;

public class Shape
{
    private int type; //0 circle, 1 rectangle
    private int centerX;
    private int centerY;
    private int radius;
    private int speed;
    private int direction;
    private int color;

    private int width, height;
    private Random r = new Random();

    public Shape(int width, int height)
    {
        this.width = width;
        this.height = height;

        type = r.nextInt(2); //0 circle, 1 rectangle
        centerX = r.nextInt(width);
        centerY = r.nextInt(height);
        radius = r.nextInt(75) + 25;
        speed = r.nextInt(15) + 5; //delta
        direction = r.nextInt(4); //0 up, 1 down, 2 left, 3 right
        color = Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    //Continues moving to opposite boundary
    public void move()
    {
        if(direction == 0) { //up
            centerY = centerY - speed;
            if(centerY < 0-radius) centerY = 2*radius + height;
        } else if(direction == 1) { //down
            centerY = centerY + speed;
            if(centerY > height+radius) centerY = -2*radius;
        } else if(direction == 2) { //left
            centerX = centerX - speed;
            if(centerX < 0-radius) centerX = 2*radius + width;
        } else if(direction == 3) { //right
            centerX = centerX + speed;
            if(centerX > width+radius) centerX = -2*radius;
        }
    }

    public int getCenterX()
    {
        return centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }

    public int getRadius()
    {
        return radius;
    }

    public int getType() {
        return type;
    }

    public int getColor() {
        return color;
    }
}
