package com.cosc426.movingshapes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import android.util.DisplayMetrics;


public class MainActivity extends AppCompatActivity
{
    private Game game;
    private GameView gameView;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Get screen width and height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        //Create game
        game = new Game(width, height);

        //Create gameView
        gameView = new GameView(this, game);
        setContentView(gameView);

        //Schedule animation
        Timer timer = new Timer();
        GameTimerTask task = new GameTimerTask(game, gameView);
        timer.schedule(task, 2000, 20);
    }
}