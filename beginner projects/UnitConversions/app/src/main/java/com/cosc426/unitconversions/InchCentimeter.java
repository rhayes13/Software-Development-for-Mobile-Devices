package com.cosc426.unitconversions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InchCentimeter extends AppCompatActivity {

    private Button buttonBack;
    private EditText inchIn, cmIn;
    private TextView inchOut, cmOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inch_centimeter);
        setupView();//Initializes button for onclick listener
    }

    private class TextChangedHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        //Update text as user types
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //For inch to centimeters
            String inchInString = inchIn.getText().toString();
            try {
                float inch = Float.parseFloat(inchInString);
                double output = inch*2.54;
                cmOut.setText(String.format("%.2f", output)); //2 decimal places
            } catch (Exception e) {}

            //For centimeters to inch
            String cmInString = cmIn.getText().toString();
            try {
                float cms = Float.parseFloat(cmInString);
                double output = cms/2.54;
                inchOut.setText(String.format("%.2f", output)); //2 decimal places
            } catch (Exception e) {}
        }

        @Override
        public void afterTextChanged(Editable s) {
            //If output is cleared
            if(inchIn.getText().toString().trim().equals("")) {
                cmOut.setText("");
            }

            if(cmIn.getText().toString().trim().equals("")) {
                inchOut.setText("");
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
        inchIn = (EditText)findViewById(R.id.inchIn);
        cmIn = (EditText)findViewById(R.id.cmIn);
        inchOut = (TextView) findViewById(R.id.inchOut);
        cmOut = (TextView) findViewById(R.id.cmOut);

        //Set onTextChangeListener for EditText
        TextChangedHandler changed = new TextChangedHandler();
        inchIn.addTextChangedListener(changed);
        cmIn.addTextChangedListener(changed);


    }
}