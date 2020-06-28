package com.cosc426.seventhapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Question q;
    private TextView questionTv;
    private RadioGroup answersRg;
    private RadioButton ans1Rb;
    private RadioButton ans2Rb;
    private RadioButton ans3Rb;
    private RadioButton ans4Rb;
    private static final int RB1ID = 1; // IDs to determine which button is selected
    private static final int RB2ID = 2;
    private static final int RB3ID = 3;
    private static final int RB4ID = 4;
    private Button submitButton;
    private Button nextButton;
    private TextView submitMessage;
    private TextView numCorrect;
    private int key, correctKey, id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initialize elements
        initialize(); //prepare first question when app starts

        // key = 1-10 (For each set of Q/A called from Question class)
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                correctKey = q.getCorrectAns(key); // gets correct answer from answer key
                id = answersRg.getCheckedRadioButtonId(); // gets user's selection
                // if selections match, answer is correct; sets color to red if wrong or green if correct
                if(correctKey == id) {
                    q.incrementCorrect(); // if correct
                    submitMessage.setTextColor(Color.rgb(0, 255, 0));
                    submitMessage.setText("Correct");
                } else if(correctKey != id || id == -1) {
                    submitMessage.setTextColor(Color.rgb(255, 0, 0));
                    submitMessage.setText("Incorrect");
                }

                // Increment total and set numCorrect TextView
                q.incrementTotal();
                numCorrect.setText(q.getTotalCorrect() + "/" + q.getTotalQuestions());

                // to prevent cheating
                submitButton.setEnabled(false);
                nextButton.setEnabled(true);
            }
        });


        //after next click..change message to SubmitAnswer and disable Next button
        // increment key to re-initialize the view with the next key
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                submitMessage.setTextColor(Color.rgb(0, 0, 0));
                submitMessage.setText("Submit answer");
                key++;

                initialize();
            }
        });
    }

    // Initializes based on key value
    private void initialize() {
        answersRg.clearCheck(); // reset checked Radio button

        // sets Q/A TextView based on key
        questionTv.setText(q.getQuestion(key));
        ans1Rb.setText(q.getAnsOneText(key));
        ans2Rb.setText(q.getAnsTwoText(key));
        ans3Rb.setText(q.getAnsThreeText(key));
        ans4Rb.setText(q.getAnsFourText(key));

        // Enable submit, disable next to prevent cheating
        submitButton.setEnabled(true);
        nextButton.setEnabled(false);
    }

    // Original function to initialize elements in view and assigns IDs
    private void setupView() {
        q = new Question();
        questionTv = (TextView)findViewById(R.id.questionTextView);
        answersRg = (RadioGroup)findViewById(R.id.answersRadioGroup);
        ans1Rb = (RadioButton)findViewById(R.id.rb1);
        ans2Rb = (RadioButton)findViewById(R.id.rb2);
        ans3Rb = (RadioButton)findViewById(R.id.rb3);
        ans4Rb = (RadioButton)findViewById(R.id.rb4);
        submitButton = (Button)findViewById(R.id.buttonSubmit);
        nextButton = (Button)findViewById(R.id.buttonNext);
        submitMessage = (TextView)findViewById(R.id.correctTextView);
        numCorrect = (TextView)findViewById(R.id.questionNumberTextView);
        key = 1;
        correctKey = 0;
        id = 0;

        // Assign the IDs 1-4
        ans1Rb.setId(RB1ID);
        ans2Rb.setId(RB2ID);
        ans3Rb.setId(RB3ID);
        ans4Rb.setId(RB4ID);
    }
}
