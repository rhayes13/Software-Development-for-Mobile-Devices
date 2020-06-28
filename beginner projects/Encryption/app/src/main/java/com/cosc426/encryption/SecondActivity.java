package com.cosc426.encryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private Model model = MainActivity.model; //model from main
    private EditText keyEt;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        updateView(); //Initialize
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
                //on Submit
                updateKey();
                finish();
        }
    }

    //Get current key
    private void updateView() {
        keyEt = (EditText)findViewById(R.id.etKey);
        keyEt.setText(Integer.toString(model.getKey()) + "");

        //Setup submit button to return to main screen
        ButtonHandler temp = new ButtonHandler();
        submitButton = (Button)findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(temp);
    }

    //Update key in case it was changed
    private void updateKey() {
        keyEt = (EditText)findViewById(R.id.etKey);
        String keyString = keyEt.getText().toString();
        int key = Integer.parseInt(keyString);
        model.set(key);

        model.savePreferences(this); //save key
    }
}
