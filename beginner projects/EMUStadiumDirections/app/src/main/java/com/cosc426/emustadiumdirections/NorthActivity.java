package com.cosc426.emustadiumdirections;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NorthActivity extends AppCompatActivity {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_north);
        setupView();//Initializes button for onclick listener
    }

    //onClickListener to return to previous screen
    //Current screen disappears, previous screen reappears rotating
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            finish();
            overridePendingTransition(R.anim.back_animation, 0);
        }
    }

    private void setupView() {
        ButtonHandler temp = new ButtonHandler();
        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(temp);
    }
}
