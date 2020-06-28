package com.cosc426.universitycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FifthActivity extends AppCompatActivity {

    private Button buttonBack;
    private TextView tvOut;
    private int credits;
    private String status, dorm, dining;
    private Tuition t = MainActivity.t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        setupView(); // Initializes view
        updateTextView();
    }

    private void updateTextView() {
        //Use Tuition object to update TextView
        tvOut.setText("Credits: " + t.getCredits() + '\n' + '\n'
                      + "Status: " + t.getStatus() + '\n' + '\n'
                      + "Dorm: " + t.getDorm() + '\n' + '\n'
                      + "Dining: " + t.getDining() + '\n' + '\n'
                      + "Total: $" + t.getTotal());

    }

    //onClickListener to return to previous screen
    private class ButtonHandlerPrev implements View.OnClickListener {
        public void onClick(View v) {
            finish();
        }
    }

    private void setupView() {
        tvOut = (TextView)findViewById(R.id.tv1);

        ButtonHandlerPrev prev = new ButtonHandlerPrev();
        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(prev);
    }
}
