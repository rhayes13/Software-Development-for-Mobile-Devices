package com.cosc426.unitconversions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MileKilometerActivity extends AppCompatActivity {

    private Button buttonBack;
    private EditText mileIn, kilometerIn;
    private TextView mileOut, kilometerOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mile_kilometer);
        setupView();//Initializes button for onclick listener
    }

    private class TextChangedHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        //Update text as user types
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //For miles to kilometers
            String mileInString = mileIn.getText().toString();
            try {
                float miles = Float.parseFloat(mileInString);
                double output = miles*1.60934;
                kilometerOut.setText(String.format("%.2f", output)); //2 decimal places
            } catch (Exception e) {}

            //For kilometers to miles
            String kiloInString = kilometerIn.getText().toString();
            try {
                float kilos = Float.parseFloat(kiloInString);
                double output = kilos/1.60934;
                mileOut.setText(String.format("%.2f", output)); //2 decimal places
            } catch (Exception e) {}
        }

        @Override
        public void afterTextChanged(Editable s) {
            //If output is cleared
            if(mileIn.getText().toString().trim().equals("")) {
                kilometerOut.setText("");
            }

            if(kilometerIn.getText().toString().trim().equals("")) {
                mileOut.setText("");
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
        mileIn = (EditText)findViewById(R.id.mileIn);
        kilometerIn = (EditText)findViewById(R.id.kilometerIn);
        mileOut = (TextView) findViewById(R.id.mileOut);
        kilometerOut = (TextView) findViewById(R.id.kilometerOut);

        //Set onTextChangeListener for EditText
        TextChangedHandler changed = new TextChangedHandler();
        mileIn.addTextChangedListener(changed);
        kilometerIn.addTextChangedListener(changed);


    }
}