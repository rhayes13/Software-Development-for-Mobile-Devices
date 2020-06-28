package com.cosc426.doodleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private Model m;
    private GraphicsView v;
    private GestureDetector gestureDetector;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        m = new Model();
        v = new GraphicsView(this, m, width, height);
        setContentView(v);

        TouchHandler temp = new TouchHandler();
        gestureDetector = new GestureDetector(this, temp);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();

        //If rectangle is tapped
        if(x > width-200 && x < width-50  && y > height-240 && y < height-50) {
            gestureDetector.onTouchEvent(event);
        }

        if (action == MotionEvent.ACTION_DOWN) {
            //draw
            m.addPoint(x, y-240);
            return true;
        } else if (action == MotionEvent.ACTION_MOVE) {
            //draw
            m.addPoint(x, y-240);
        }

        v.postInvalidate();
        return true;
    }

    private class TouchHandler extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent event)
        {
            m.nextColor();
            v.postInvalidate();
            return true;
        }
    }
}
