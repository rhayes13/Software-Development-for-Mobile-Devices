package com.cosc426.rgbvisualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar redSeek, greenSeek, blueSeek;
    private TextView redText, greenText, blueText, box;
    private int r, g, b;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initialize elements
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch(seekBar.getId()) {
            case R.id.redSeek:
                r = progress;
                break;
            case R.id.greenSeek:
                g = progress;
                break;
            case R.id.blueSeek:
                b = progress;
                break;
        }

        redText.setText(Integer.toString(r));
        greenText.setText(Integer.toString(g));
        blueText.setText(Integer.toString(b));
        box.setBackgroundColor(android.graphics.Color.rgb(r, g, b));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    // Initialize elements
    private void setupView() {
        r = 0;
        g = 0;
        b = 0;
        redSeek = (SeekBar)findViewById(R.id.redSeek);
        greenSeek = (SeekBar)findViewById(R.id.greenSeek);
        blueSeek = (SeekBar)findViewById(R.id.blueSeek);
        redSeek.setOnSeekBarChangeListener(this);
        greenSeek.setOnSeekBarChangeListener(this);
        blueSeek.setOnSeekBarChangeListener(this);
        redText = (TextView)findViewById(R.id.redTv);
        greenText = (TextView)findViewById(R.id.greenTv);
        blueText = (TextView)findViewById(R.id.blueTv);
        box = (TextView)findViewById(R.id.boxTv);
    }
}
