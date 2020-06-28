package com.cosc426.sortinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private final int SIZE = 10;
    private Game game;
    private AppInterface appInterface;
    private GestureDetector gestureDetector;
    private boolean gameOver; //to disable gestures
    private int swapsLeft;
    private int windowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = new Game();

        appInterface = new AppInterface(this);
        setContentView(appInterface);
        appInterface.updateWindow(); //Initialize window for swapping elements
        appInterface.showCurrent(game.getCurrent());

        TouchHandler temp = new TouchHandler();
        gestureDetector = new GestureDetector(this, temp);

        swapsLeft = 45;
        gameOver = false;
    }

    public boolean onTouchEvent(MotionEvent event){
        if(!gameOver) gestureDetector.onTouchEvent(event); //to disable gestures
        return true;
    }

    private class TouchHandler extends GestureDetector.SimpleOnGestureListener{
        public boolean onSingleTapConfirmed(MotionEvent event){
            if(gameOver) return false;
            appInterface.updateWindow();
            return true;
        }

        public boolean onDoubleTap(MotionEvent event)
        {
            windowView = appInterface.getWindowView();
            game.exchange(windowView, windowView + 1);
            appInterface.showCurrent(game.getCurrent());
            swapsLeft--;
            if(swapsLeft < 0) swapsLeft = 0;
            if(swapsLeft == 0) {
                gameOver = true; //disables gestures
                appInterface.lossMessage();
            }
            appInterface.updateTextView(swapsLeft);

            if(game.solved()) {
                gameOver = true; //disables gestures
                appInterface.winMessage(swapsLeft);
                return false;
            }
            return true;
        }
    }
}