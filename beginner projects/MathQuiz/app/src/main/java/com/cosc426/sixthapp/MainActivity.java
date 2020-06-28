package com.cosc426.sixthapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    MathGenerator mg;
    private int answer, key;
    private TextView num1TextView;
    private TextView num2TextView;
    private TextView operatorTextView;
    private TextView equalsTextView;

    private EditText answerEditText;

    private Button submitButton;
    private Button nextButton;
    private TextView isCorrectTextView;
    private TextView counterTextView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initializes elements
        initialize(); // Initializes first question

        // Submit button updates color of isCorrectTextView and increments numbers
        // Checks if answer is correct
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String answerString = answerEditText.getText().toString();
                // Try/catch statement for empty EditText
                try {
                    answer = Integer.parseInt(answerString);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }

                int id = mg.getCorrectAnswer();

                // if selections match, answer is correct; sets color to red if wrong or green if correct
                if(answer == id) {
                    mg.incrementNumCorrect(); // if correct
                    isCorrectTextView.setTextColor(Color.rgb(0, 255, 0));
                    isCorrectTextView.setText("Correct");
                } else if(answer != mg.getCorrectAnswer()) {
                    isCorrectTextView.setTextColor(Color.rgb(255, 0, 0));
                    isCorrectTextView.setText("Incorrect");
                }

                // Increment total and set counterTextView
                mg.incrementNumTotal();
                counterTextView.setText(mg.getNumCorrect() + "/" + mg.getNumTotal());

                // to prevent cheating
                submitButton.setEnabled(false);
                nextButton.setEnabled(true);
            }
        });


        //after next is clicked..change message to SubmitAnswer and disable Next button
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isCorrectTextView.setTextColor(Color.rgb(0, 0, 0));
                isCorrectTextView.setText("Submit answer");
                initialize(); // call initialize for new question
            }
        });

    }

    // Initializes first question
    private void initialize() {
        key = mg.generateRand();
        mg.generateOperator(key); // generate operator
        mg.generateNum1(key); // generate num1, num2, and correctAnswer
        mg.generateNum2(key);
        mg.generateCorrectAnswer(key);

        // Set TextView for num1, num2, operator, and equals;   Set EditText to empty
        num1TextView.setText(mg.getNum1());
        num2TextView.setText(mg.getNum2());
        operatorTextView.setText(mg.getOperator());
        equalsTextView.setText(mg.getEquals());
        answerEditText.setText(null);

        // Enable submit, disable next to prevent cheating
        submitButton.setEnabled(true);
        nextButton.setEnabled(false);
    }

    // Initializes elements
    private void setupView() {
        key = 0;
        answer = 0;
        mg = new MathGenerator();
        num1TextView = (TextView)findViewById(R.id.num1Tv);
        num2TextView = (TextView)findViewById(R.id.num2Tv);
        operatorTextView = (TextView)findViewById(R.id.operatorTv);
        equalsTextView = (TextView)findViewById(R.id.equalsTv);
        answerEditText = (EditText)findViewById(R.id.outputEt);
        submitButton = (Button)findViewById(R.id.submitButton);
        nextButton = (Button)findViewById(R.id.nextButton);
        isCorrectTextView = (TextView)findViewById(R.id.correctTv);
        counterTextView = (TextView)findViewById(R.id.counterTv);
    }
}
