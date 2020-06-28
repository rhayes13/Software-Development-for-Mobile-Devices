package com.cosc426.thirdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private TextView principalTextView;
    private TextView annualAdditionTextView;
    private TextView numYearsTextView;
    private TextView rateOfReturnTextView;
    private TextView finalAmountTextView;

    private EditText principalEditText;
    private EditText annualAdditionEditText;
    private EditText numYearsEditText;
    private EditText rateOfReturnEditText;
    private TextView outputTextView;

    private Formula f;
    private Float principalFloat;
    private Float annualFloat;
    private Float yearsFloat;
    private Float rateFloat;

    private Button computeButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();

        /* For each EditText, try to parseFloat and setValue(); if empty, catch
            NumberFormatException and set box to red color. Otherwise set color to default */
        computeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String principalString = principalEditText.getText().toString();
                    principalFloat = Float.parseFloat(principalString);
                    principalEditText.setBackgroundColor(Color.rgb(238, 238, 238));
                    f.setCurrent(principalFloat);
                } catch (NumberFormatException e) {
                    principalEditText.setBackgroundColor(Color.rgb(255, 0, 0));
                }

                try {
                    String annualString = annualAdditionEditText.getText().toString();
                    annualFloat = Float.parseFloat(annualString);
                    annualAdditionEditText.setBackgroundColor(Color.rgb(238, 238, 238));
                    f.setAnnual(annualFloat);
                } catch (NumberFormatException e) {
                    annualAdditionEditText.setBackgroundColor(Color.rgb(255, 0, 0));
                }

                try {
                    String yearsString = numYearsEditText.getText().toString();
                    yearsFloat = Float.parseFloat(yearsString);
                    numYearsEditText.setBackgroundColor(Color.rgb(238, 238, 238));
                    f.setYears(yearsFloat);
                } catch (NumberFormatException e) {
                    numYearsEditText.setBackgroundColor(Color.rgb(255, 0, 0));
                }

                try {
                    String rateString = rateOfReturnEditText.getText().toString();
                    rateFloat = Float.parseFloat(rateString);
                    rateOfReturnEditText.setBackgroundColor(Color.rgb(238, 238, 238));
                    f.setRate(rateFloat);
                    if (rateFloat <= 0.0) {
                        rateOfReturnEditText.setBackgroundColor(Color.rgb(255, 0, 0));
                    }
                    f.setRate(rateFloat);
                } catch (NumberFormatException e) {
                    rateOfReturnEditText.setBackgroundColor(Color.rgb(255, 0, 0));
                }

                /* Set outputTextView with Formula class method call compute() */
                String finalInterest =
                        Integer.toString(f.compute(f.getCurrent(), f.getAnnual(), f.getYears(), f.getRate()));
                outputTextView.setText("$" + finalInterest);
            }
        });
    }

    /* Initializes elements and variables */
    private void setupView() {
        principalTextView = (TextView) findViewById(R.id.labelPrincipal);
        annualAdditionTextView = (TextView) findViewById(R.id.labelAnnualAddition);
        numYearsTextView = (TextView) findViewById(R.id.labelNumberOfYears);
        rateOfReturnTextView = (TextView) findViewById(R.id.labelRateOfReturn);
        finalAmountTextView = (TextView) findViewById(R.id.labelCompoundInterest);

        principalEditText = (EditText) findViewById(R.id.editPrincipal);
        annualAdditionEditText = (EditText) findViewById(R.id.editAnnualAddition);
        numYearsEditText = (EditText) findViewById(R.id.editNumberOfYears);
        rateOfReturnEditText = (EditText) findViewById(R.id.editRateOfReturn);
        outputTextView = (TextView) findViewById(R.id.outputFinal);

        principalFloat = 0.0f;
        annualFloat = 0.0f;
        yearsFloat = 0.0f;
        rateFloat = 0.0f;
        f = new Formula();

        computeButton = (Button) findViewById(R.id.buttonCompute);
    }
}