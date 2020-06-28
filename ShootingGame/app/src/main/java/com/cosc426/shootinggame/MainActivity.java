package com.cosc426.shootinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import java.util.Timer;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends Activity
{
    private Game game;
    private GameView gameView;
    private GestureDetector gestureDetector;
    public static SoundPool soundPool;
    public static int soundId;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //explosion sound
        SoundPool.Builder soundPoolBuilder = new SoundPool.Builder();
        soundPool = soundPoolBuilder.build();
        soundId = soundPool.load(this, R.raw.explosion, 1);

        //Create game, gameView
        game = new Game();
        gameView = new GameView(this, game);
        setContentView(gameView);

        //Scheduler for animation
        Timer timer = new Timer();
        GameTimerTask task = new GameTimerTask(game, gameView);
        timer.schedule(task, 3000, 20);

        TouchHandler temp = new TouchHandler();
        gestureDetector = new GestureDetector(this, temp);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        gestureDetector.onTouchEvent(event);

        return true;
    }

    private class TouchHandler extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onDoubleTap(MotionEvent event)
        {
            game.fire();

            return true;
        }
    }
}