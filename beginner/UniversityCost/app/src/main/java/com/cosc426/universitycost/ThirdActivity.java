package com.cosc426.universitycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ThirdActivity extends AppCompatActivity {

    private Button buttonNext, buttonBack;
    private RadioButton rbUndergrad, rbGrad;
    private Tuition t = MainActivity.t;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setupView(); // Initializes view
    }

    //onClickListener to go to next screen
    private class ButtonHandlerNext implements View.OnClickListener {
        public void onClick(View v) {
            try {
                //Parse and set values
                if(rbUndergrad.isChecked()) {
                    t.setStatus("undergraduate");
                }
                if(rbGrad.isChecked()) {
                    t.setStatus("graduate");
                }

                //Call next activity intent
                Intent fourthActivity = new Intent(getApplicationContext(), FourthActivity.class);
                startActivity(fourthActivity);

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
        status = t.getStatus();
        t.setStatus(status);
        rbGrad = (RadioButton)findViewById(R.id.rbGraduate);
        rbUndergrad = (RadioButton)findViewById(R.id.rbUndergrad);
        if(t.getStatus() == "undergraduate") {
            rbUndergrad.setChecked(true);
        }
        if(t.getStatus() == "graduate") {
            rbGrad.setChecked(true);
        }

        ButtonHandlerNext next = new ButtonHandlerNext();
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(next);

        ButtonHandlerPrev prev = new ButtonHandlerPrev();
        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(prev);
    }
}
