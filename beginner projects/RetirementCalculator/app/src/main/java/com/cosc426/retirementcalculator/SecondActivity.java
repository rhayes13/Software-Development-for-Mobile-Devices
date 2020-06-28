package com.cosc426.retirementcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView yearsTv, amountTv;
    private Button backButton;
    private double previous;
    private int principal, addition, years;
    private double rate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setupView(); //Initialize view
        updateView(); //Update view with extras
    }

    private void updateView() {

        //Update years text view
        String yearsString = "Years" + '\n' +'\n';
        for(int i = 1; i <= years; i++) {
            yearsString += String.valueOf(i) + '\n' + '\n';
        }
        yearsTv.setText(yearsString);

        //Update amount text view
        String amountString = "Amount" + '\n' + '\n';
        for(double i = 1; i <= years; i+=1.0) {
            //generate interest output , base case for first year
            if(i == 1.0){
                previous = addition + principal*Math.pow((1 + (rate/100)), 1);
                int finalInterest = (int)previous;
                amountString += "$" + String.valueOf(finalInterest) + '\n' + '\n';
            } else {
                previous = addition + previous*Math.pow((1 + (rate / 100)), 1);
                int finalInterest = (int)previous;
                amountString += "$" + String.valueOf(finalInterest) + '\n' + '\n';
            }
        }
        amountTv.setText(amountString);
    }

    //onClickListener to return to main screen
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            finish();
        }
    }

    //Initializes view
    private void setupView() {
        Intent intent = getIntent();
        previous = 0.00d;
        principal = intent.getIntExtra("principal", 0);
        addition = intent.getIntExtra("addition", 0);
        rate = intent.getDoubleExtra("rate", 0);
        years = intent.getIntExtra("years", 0);
        yearsTv = (TextView)findViewById(R.id.yearsTv);
        amountTv = (TextView)findViewById(R.id.amountTv);

        ButtonHandler temp = new ButtonHandler();
        backButton = (Button)findViewById(R.id.buttonBack);
        backButton.setOnClickListener(temp);
    }
}
