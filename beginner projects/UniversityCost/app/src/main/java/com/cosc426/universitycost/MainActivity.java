package com.cosc426.universitycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button nextButton;
    public static Tuition t; //global static Tuition object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initializes view
    }

    //onClickListener to go to second screen
    private class ButtonHandlerNext implements View.OnClickListener {
        public void onClick(View v) {
            try {
                //Call next activity intent
                Intent secondActivity = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(secondActivity);

            } catch(Exception e) {}
        }
    }

    private void setupView() {
        t = new Tuition();
        ButtonHandlerNext next = new ButtonHandlerNext();
        nextButton = (Button)findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(next);
    }
}
