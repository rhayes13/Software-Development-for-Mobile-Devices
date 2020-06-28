package com.cosc426.slidingpuzzle;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;


public class MainActivity extends AppCompatActivity
{
    private final int SIZE = 3;
    private Game game;
    private AppInterface appInterface;
    private GestureDetector gestureDetector;
    private boolean gameOver; //to disable gestures

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //create game
        game = new Game();
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        int width = screenSize.x/4;

        //create event handler
        TouchHandler temp = new TouchHandler();
        gestureDetector = new GestureDetector(this, temp);

        //create interface
        appInterface = new AppInterface(this, SIZE, width);
        setContentView(appInterface);

        //draw initial board
        appInterface.drawBoard(game.getBoard());

        gameOver = false;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if(!gameOver) gestureDetector.onTouchEvent(event);
        return true;
    }

    private class TouchHandler extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
        {
            float startX = event1.getX();
            float startY = event1.getY();
            float endX = event2.getX();
            float endY = event2.getY();
            int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

            //if fling was inside board
            if(startX >= 125 && startX <= 948 && endX >= 125 && endX <= 948 && startY >= 360 && startY <= 1185 && endY >= 360 && endY <= 1185) {
                //find row/column of (x1, y1), (y2, x2)
                y1 = getColumn(startX); //start
                x1 = getRow(startY);

                y2 = getColumn(endX); //end
                x2 = getRow(endY);

                //make move in the board if check passes
                game.check(x1, y1, x2, y2);

                //draw updated board
                appInterface.drawBoard(game.getBoard());

                //Check if game was won
                if(game.gameWon()) {
                    appInterface.gameWon();
                    gameOver = true;
                }
            }

            return true;
        }

        private int getColumn(float xFloat) {
            int x = (int)xFloat;
            if(x <= 398) x = 0;                 //col 0
            if(x > 398 && x <= 675) x = 1;      //col 1
            if(x > 675) x = 2;                  //col 2
            return x;
        }

        private int getRow(float yFloat) {
            int y = (int)yFloat;
            if(y <= 634) y = 0;                 //row 0
            if(y > 634 && y <= 911) y = 1;      //row 1
            if(y > 911) y = 2;                  //row 2
            return y;
        }

    }
}
