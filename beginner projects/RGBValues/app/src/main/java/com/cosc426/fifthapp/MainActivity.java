package com.cosc426.fifthapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;
import android.widget.EditText;
import android.graphics.Color;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {

    private TextView colorBoxTextView;
    private EditText editRed;
    private EditText editGreen;
    private EditText editBlue;
    private String red, green, blue;
    private int redInt, greenInt, blueInt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initialize elements

        // red, green, and blue all initialized to 0 (although I think this bit is unnecessary)
        red = editRed.getText().toString();
        redInt = Integer.parseInt(red);

        green = editGreen.getText().toString();
        greenInt = Integer.parseInt(green);

        blue = editBlue.getText().toString();
        blueInt = Integer.parseInt(blue);

        // Initialize color to black based on rgb values
        colorBoxTextView.setBackgroundColor(Color.rgb(redInt, greenInt, blueInt));

        /* TextChangedListener for each RGB box. When text is changed, update methods are called */
        editRed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateRed();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editGreen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateGreen();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editBlue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateBlue();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Each of these methods updates color when called
    private void updateRed() {
        red = editRed.getText().toString(); //text to string

        // try to parseInt, catch if empty string; if value > 255, treat as 255
        try {
            redInt = Integer.parseInt(red);
            if (redInt > 255) redInt = 255;
        }
        catch (NumberFormatException e)
        {
            redInt = 0;
        }

        colorBoxTextView.setBackgroundColor(Color.rgb(redInt, greenInt, blueInt));
    }

    private void updateGreen() {
        green = editGreen.getText().toString();

        // try to parseInt, catch if empty string; if value > 255, treat as 255
        try {
            greenInt = Integer.parseInt(green);
            if (greenInt > 255) greenInt = 255;
        }
        catch (NumberFormatException e)
        {
            greenInt = 0;
        }

        colorBoxTextView.setBackgroundColor(Color.rgb(redInt, greenInt, blueInt));
    }

    private void updateBlue() {
        blue = editBlue.getText().toString();

        // try to parseInt, catch if empty string; if value > 255, treat as 255
        try {
            blueInt = Integer.parseInt(blue);
            if (blueInt > 255) blueInt = 255;
        }
        catch (NumberFormatException e)
        {
            blueInt = 0;
        }

        colorBoxTextView.setBackgroundColor(Color.rgb(redInt, greenInt, blueInt));
    }

    // Initialize elements
    private void setupView() {
        colorBoxTextView = (TextView)findViewById(R.id.colorBox);
        editRed = (EditText)findViewById(R.id.editR);
        editGreen = (EditText)findViewById(R.id.editG);
        editBlue = (EditText)findViewById(R.id.editB);
        colorBoxTextView.setBackgroundColor(Color.rgb(0, 0, 0));
    }
}
