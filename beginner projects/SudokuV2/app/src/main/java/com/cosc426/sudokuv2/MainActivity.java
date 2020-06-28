package com.cosc426.sudokuv2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;

import android.text.TextWatcher;
import android.text.Editable;

public class MainActivity extends AppCompatActivity
{
    private Game game;
    private AppInterface appInterface;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //create model
        game = new Game();
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        int width = screenSize.x/9;

        //create interface, draw initial board
        appInterface = new AppInterface(this, 9, width);
        setContentView(appInterface);

        //attach event handlers
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
            {
                TextChangeHandler temp = new TextChangeHandler(i, j);
                appInterface.setTextChangeHandler(temp, i, j);
            }
    }

    private class TextChangeHandler implements TextWatcher
    {
        private int x;
        private int y;

        public TextChangeHandler(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public void afterTextChanged(Editable e)
        {
            //x, y is location of edit box where the event originated

            //get input from x, y

            //Depending on the input, modify model and interface,
            //cover all cases (3 or 4 cases)
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        public void onTextChanged(CharSequence s, int start, int before, int after)
        {

        }
    }

}