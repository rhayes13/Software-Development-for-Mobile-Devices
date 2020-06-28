package com.cosc426.doodleapp;

import android.graphics.Color;

import java.util.ArrayList;

public class Model {

    private ArrayList<Point> points; //list of points
    private String colorBox;

    public Model() {
        points = new ArrayList<Point>();
        colorBox = "#000000"; //Initial color black
    }

    //Adds new point to list
    public void addPoint(float x, float y) {
        Point p = new Point(x, y, colorBox);
        points.add(p);
    }

    //Cycles through int value of 8 colors
    public void nextColor() {
        if(colorBox == "#000000") {
            colorBox = "#FFFFFF";
        } else if(colorBox == "#FFFFFF") {
            colorBox = "#808080";
        } else if(colorBox == "#808080") {
            colorBox = "#FF0000";
        } else if(colorBox == "#FF0000") {
            colorBox = "#00FF00";
        } else if(colorBox == "#00FF00") {
            colorBox = "#0000FF";
        } else if(colorBox == "#0000FF") {
            colorBox = "#FFFF00";
        } else if(colorBox == "#FFFF00") {
            colorBox = "#A52A2A";
        } else if(colorBox == "#A52A2A") {
            colorBox = "#000000";
        }
    }

    public ArrayList<Point> getList() {
        return points;
    }

    public String boxColor() {
        return colorBox;
    }

}
