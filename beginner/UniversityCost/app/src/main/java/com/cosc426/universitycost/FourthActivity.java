package com.cosc426.universitycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class FourthActivity extends AppCompatActivity {

    private Button buttonNext, buttonBack;
    private CheckBox dormCb, diningCb;
    private Tuition t = MainActivity.t;
    private String dorm, dining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        setupView(); // Initializes view
    }

    //onClickListener to go to next screen
    private class ButtonHandlerNext implements View.OnClickListener {
        public void onClick(View v) {
            try {
                //Parse values and set
                if(dormCb.isChecked()) {
                    t.setDorm("yes");
                }
                else {
                    t.setDorm("no");
                }

                if(diningCb.isChecked()) {
                    t.setDining("yes");
                }
                else {
                    t.setDining("no");
                }


                //Call next activity intent
                Intent fifthActivity = new Intent(getApplicationContext(), FifthActivity.class);
                startActivity(fifthActivity);

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
        dormCb = (CheckBox) findViewById(R.id.cbDormitory);
        diningCb = (CheckBox) findViewById(R.id.cbDining);

        //To ensure values are kept between screens
        dorm = t.getDorm();
        dining = t.getDining();
        t.setDorm(dorm);
        t.setDining(dining);
        if(t.getDorm() == "yes") {
            dormCb.setChecked(true);
        }
        if(t.getDining() == "yes") {
            diningCb.setChecked(true);
        }

        ButtonHandlerNext next = new ButtonHandlerNext();
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(next);

        ButtonHandlerPrev prev = new ButtonHandlerPrev();
        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(prev);
    }
}
