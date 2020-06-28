package com.cosc426.doodleapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class GraphicsView extends View {

    private Model m;
    private Paint brush;
    private int width;
    private int height;
    private float x;
    private float y;
    private String color;

    public GraphicsView(Context context, Model m, int width, int height) {
        super(context);
        this.m = m;
        this.width = width;
        this.height = height;
        brush = new Paint();
    }

    protected void onDraw(Canvas canvas) {
        //draw white background
        brush.setStyle(Paint.Style.FILL);
        brush.setColor(Color.WHITE);
        canvas.drawRect(0, 0, width, height, brush);

        ArrayList<Point> points = m.getList(); //draw each point in the list
        for (int i = 0; i < points.size(); i++) {
            x = points.get(i).x;
            y = points.get(i).y;
            color = points.get(i).color;
            brush.setColor(Color.parseColor(color));
            brush.setStrokeJoin(Paint.Join.ROUND);
            canvas.drawCircle(x, y, 15, brush);
        }


        color = m.boxColor();
        brush.setColor(Color.parseColor(color));
        canvas.drawRect(width-200, height-450, width-50, height-300, brush);
    }
}
