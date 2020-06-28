package com.cosc426.unitconversions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FeetMeterActivity extends AppCompatActivity {

    private Button buttonBack;
    private EditText feetIn, meterIn;
    private TextView feetOut, meterOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feet_meter);
        setupView();//Initializes button for onclick listener
    }

    private class TextChangedHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        //Update text as user types
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //For feet to meters
            String feetInString = feetIn.getText().toString();
            try {
                float feet = Float.parseFloat(feetInString);
                double output = feet/3.28084;
                meterOut.setText(String.format("%.2f", output)); //2 decimal places
            } catch (Exception e) {}

            //For meters to feet
            String meterInString = meterIn.getText().toString();
            try {
                float meters = Float.parseFloat(meterInString);
                double output = meters*3.28084;
                feetOut.setText(String.format("%.2f", output)); //2 decimal places
            } catch (Exception e) {}
        }

        @Override
        public void afterTextChanged(Editable s) {
            //If output is cleared
            if(feetIn.getText().toString().trim().equals("")) {
                meterOut.setText("");
            }

            if(meterIn.getText().toString().trim().equals("")) {
                feetOut.setText("");
            }
        }
    }

    //onClickListener to return to previous screen
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            finish();
        }
    }

    private void setupView() {
        ButtonHandler temp = new ButtonHandler();
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(temp);

        //EditText/TextViews
        feetIn = (EditText)findViewById(R.id.feetIn);
        meterIn = (EditText)findViewById(R.id.meterIn);
        feetOut = (TextView) findViewById(R.id.feetOut);
        meterOut = (TextView) findViewById(R.id.meterOut);

        //Set onTextChangeListener for EditText
        TextChangedHandler changed = new TextChangedHandler();
        feetIn.addTextChangedListener(changed);
        meterIn.addTextChangedListener(changed);


    }
}