package com.cosc426.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private Button subButton;
    private Button multButton;
    private Button divButton;
    private EditText input1EditText;
    private EditText input2EditText;
    private TextView outputTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); /* Initializes each element */

        /* Call onClick for button press, catch if either input is empty */
        try {
            onClick();
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
    }

    /* onClickListener for each button with NumberFormatException for empty inputs
        If not empty, convert EditText to float values and perform operation. Update output. */
    private void onClick() {
        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float input1 = Float.parseFloat(input1EditText.getText().toString());
                    float input2 = Float.parseFloat(input2EditText.getText().toString());
                    float sum = input1 + input2;
                    outputTextView.setText(Float.toString(sum));
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }

            }
        });

        subButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float input1 = Float.parseFloat(input1EditText.getText().toString());
                    float input2 = Float.parseFloat(input2EditText.getText().toString());
                    float difference = input1 - input2;
                    outputTextView.setText(Float.toString(difference));
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        });

        multButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float input1 = Float.parseFloat(input1EditText.getText().toString());
                    float input2 = Float.parseFloat(input2EditText.getText().toString());
                    float product = input1 * input2;
                    outputTextView.setText(Float.toString(product));
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        });

        divButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float input1 = Float.parseFloat(input1EditText.getText().toString());
                    float input2 = Float.parseFloat(input2EditText.getText().toString());
                    float quotient = input1 / input2;
                    outputTextView.setText(Float.toString(quotient));
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        });
    }

    // Initializes elements and variables
    private void setupView() {
        input1EditText = (EditText)findViewById(R.id.input1);
        input2EditText = (EditText)findViewById(R.id.input2);
        addButton = (Button)findViewById(R.id.add);
        subButton = (Button)findViewById(R.id.sub);
        multButton = (Button)findViewById(R.id.mult);
        divButton = (Button)findViewById(R.id.div);
        outputTextView = (TextView)findViewById(R.id.output);
    }
}