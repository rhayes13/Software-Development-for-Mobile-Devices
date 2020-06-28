package com.cosc426.statisticscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Calculator c;
    private TextView topTv, outTv;
    private EditText inputEt;
    private Button sumButton, meanButton, medianButton, stdvButton, minB, maxB;
    private DecimalFormat df;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initializes view
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            // get EditText to array
            String inputString = inputEt.getText().toString();
            String[] split = inputString.split(" ");
            float[] numbers = new float[split.length];
            for(int i = 0; i < split.length; i++) {
                try {
                    numbers[i] = Float.parseFloat(split[i]);
                } catch(NumberFormatException e) {
                    displayError();
                    System.out.println(e);
                }
            }
            c.setArray(numbers);

            int id = v.getId();
            float temp = 0.0f;

            // If sum was clicked...call function and set temp
            if(id == R.id.sumButton) {
                temp = Float.valueOf(df.format(c.getSum()));
            }

            // If mean was clicked.... etc
            if(id == R.id.meanButton) {
                temp = Float.valueOf(df.format(c.getMean()));
            }

            if(id == R.id.medianButton) {
                temp = Float.valueOf(df.format(c.getMedian()));
            }

            if(id == R.id.stdvButton) {
                temp = Float.valueOf(df.format(c.getStdv()));
            }

            if(id == R.id.minButton) {
                temp = Float.valueOf(df.format(c.getMin()));
            }

            if(id == R.id.maxButton) {
                temp = Float.valueOf(df.format(c.getMax()));
            }

            // Update output TextView based on temp value
            String output = Float.toString(temp);
            outTv.setText(output);
        }
    }

    private void displayError() {
        String message = "Invalid input";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    // Initialize elements
    private void setupView() {
        c = new Calculator();
        topTv = (TextView)findViewById(R.id.topTv);
        outTv = (TextView)findViewById(R.id.outputTv);
        inputEt = (EditText)findViewById(R.id.inputEt);
        df = new DecimalFormat("0.00");

        // ButtonHandler listener interface
        ButtonHandler temp = new ButtonHandler();
        sumButton = (Button)findViewById(R.id.sumButton);
        sumButton.setOnClickListener(temp);
        meanButton = (Button)findViewById(R.id.meanButton);
        meanButton.setOnClickListener(temp);
        medianButton = (Button)findViewById(R.id.medianButton);
        medianButton.setOnClickListener(temp);
        stdvButton = (Button)findViewById(R.id.stdvButton);
        stdvButton.setOnClickListener(temp);
        minB = (Button)findViewById(R.id.minButton);
        minB.setOnClickListener(temp);
        maxB = (Button)findViewById(R.id.maxButton);
        maxB.setOnClickListener(temp);
    }
}
