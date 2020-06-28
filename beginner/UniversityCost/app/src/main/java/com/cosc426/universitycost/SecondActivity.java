package com.cosc426.universitycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private Button buttonNext, buttonBack;
    public static EditText creditsEt;
    private Tuition t = MainActivity.t;
    private int credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setupView(); // Initializes view
        //creditsEt.setText(credits);
    }

    //onClickListener to go to next screen
    private class ButtonHandlerNext implements View.OnClickListener {
        public void onClick(View v) {
            try {
                //Parse and set values
                String creditsString = creditsEt.getText().toString();
                credits = Integer.parseInt(creditsString);
                t.setCredits(credits);

                //Call next activity intent
                Intent thirdActivity = new Intent(getApplicationContext(), ThirdActivity.class);
                startActivity(thirdActivity);

            } catch(Exception e) {}
        }
    }

    //onClickListener to return to previous screen
    private class ButtonHandlerPrev implements View.OnClickListener {
        public void onClick(View v) {
            finish();
        }
    }

    private void setupView() {
        //To ensure values are kept between screens
        credits = t.getCredits();
        t.setCredits(credits);
        creditsEt = (EditText)findViewById(R.id.et2);
        if(creditsEt.getText().toString() != "" && t.getCredits() != 0) {
            creditsEt.setText(Integer.toString(credits));
        }

        ButtonHandlerNext next = new ButtonHandlerNext();
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(next);

        ButtonHandlerPrev prev = new ButtonHandlerPrev();
        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(prev);
    }
}
