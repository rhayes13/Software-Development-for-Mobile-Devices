package com.cosc426.retirementcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText principalEt, additionEt, rateEt, yearsEt;
    private Button buttonCompute;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initializes view
    }

    // onClickListener to parse inputs
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            try {
                String principalString = principalEt.getText().toString();
                int principal = Integer.parseInt(principalString);

                String additionString = additionEt.getText().toString();
                int addition = Integer.parseInt(additionString);

                String rateString = rateEt.getText().toString();
                double rate = Double.parseDouble(rateString);

                String yearsString = yearsEt.getText().toString();
                int years = Integer.parseInt(yearsString);

                //call second activity intent, put extras
                Intent secondActivity = new Intent(getApplicationContext(), SecondActivity.class);
                secondActivity.putExtra("principal", principal);
                secondActivity.putExtra("addition", addition);
                secondActivity.putExtra("rate", rate);
                secondActivity.putExtra("years", years);
                startActivity(secondActivity);

            } catch(Exception e) {}
        }
    }

    // Initialize necessary elements
    private void setupView() {
        principalEt = (EditText)findViewById(R.id.et0);
        additionEt = (EditText)findViewById(R.id.et1);
        rateEt = (EditText)findViewById(R.id.et2);
        yearsEt = (EditText)findViewById(R.id.et3);

        ButtonHandler temp = new ButtonHandler();
        buttonCompute = (Button)findViewById(R.id.buttonCompute);
        buttonCompute.setOnClickListener(temp);
    }
}
