package com.cosc426.movingshapes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.graphics.Canvas;

//Single frame
public class GameView extends View
{
    private Game game;
    private Paint paint;

    public GameView(Context context, Game game)
    {
        super(context);

        this.game = game;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    public void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK); //Set background color
        Shape[] shapes = game.getArray(); //Go through array and get values
        int type, centerX, centerY, radius, color;
        for(int i = 0; i < shapes.length; i++) {
            type = shapes[i].getType();
            centerX = shapes[i].getCenterX();
            centerY = shapes[i].getCenterY();
            radius = shapes[i].getRadius();
            color = shapes[i].getColor();
            paint.setColor(color);

            if(type == 0) { //draw circle
                canvas.drawCircle(centerX, centerY, radius, paint);
            } else if(type == 1) { //draw rectangle
                canvas.drawRect(centerX, centerY, centerX+(2*radius), centerY+(2*radius), paint);
            }
        }


    }
}
