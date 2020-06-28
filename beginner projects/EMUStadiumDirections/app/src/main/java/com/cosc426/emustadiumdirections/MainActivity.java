package com.cosc426.emustadiumdirections;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonNorth, buttonSouth, buttonEast, buttonWest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); //Initialize view
    }

    //onClickListener to go to next screen
    //Find which button was pressed, call that activity with correct animation
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            try {
                if(v.getId() == R.id.buttonNorth) {
                    Intent northActivity = new Intent(getApplicationContext(), NorthActivity.class);
                    startActivity(northActivity);
                    overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                } else if(v.getId() == R.id.buttonSouth) {
                    Intent southActivity = new Intent(getApplicationContext(), SouthActivity.class);
                    startActivity(southActivity);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top);
                } else if(v.getId() == R.id.buttonEast) {
                    Intent eastActivity = new Intent(getApplicationContext(), EastActivity.class);
                    startActivity(eastActivity);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else if(v.getId() == R.id.buttonWest) {
                    Intent westActivity = new Intent(getApplicationContext(), WestActivity.class);
                    startActivity(westActivity);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
            } catch(Exception e) {}
        }
    }

    private void setupView() {
        //Set up onclick Listener for buttons
        ButtonHandler temp = new ButtonHandler();
        buttonNorth = (Button)findViewById(R.id.buttonNorth);
        buttonNorth.setOnClickListener(temp);
        buttonSouth = (Button)findViewById(R.id.buttonSouth);
        buttonSouth.setOnClickListener(temp);
        buttonEast = (Button)findViewById(R.id.buttonEast);
        buttonEast.setOnClickListener(temp);
        buttonWest = (Button)findViewById(R.id.buttonWest);
        buttonWest.setOnClickListener(temp);
    }
}
