package com.cosc426.encryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {

    public static Model model;
    private EditText textBox;
    private Button encryptButton, decryptButton, keyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); //Initializes elements
    }

    public void onStart() {
        super.onStart();
        setupView();
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            try {
                //Find which button was clicked
                if(v.getId() == R.id.buttonEncrypt) {
                    //Encrypt string: get input, encrypt it, update output
                    String input = textBox.getText().toString();
                    String encrypt = model.encrypt(input);
                    textBox.setText(encrypt);
                } else if(v.getId() == R.id.buttonDecrypt) {
                    //Decrypt string: get input, decrypt it, update output
                    String input = textBox.getText().toString();
                    String decrypt = model.decrypt(input);
                    textBox.setText(decrypt);
                } else if(v.getId() == R.id.buttonKey) {
                    //Go to key screen
                    Intent secondActivity = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(secondActivity);
                }

            } catch(Exception e) {}
        }
    }

    //Initialize view on both start and create
    private void setupView() {
        //Key is read from shared prefs on model create
        model = new Model(this);

        textBox = (EditText)findViewById(R.id.et1);
        String string = textBox.getText().toString();
        textBox.setText(string);

        ButtonHandler temp = new ButtonHandler();
        encryptButton = (Button)findViewById(R.id.buttonEncrypt);
        encryptButton.setOnClickListener(temp);

        decryptButton = (Button)findViewById(R.id.buttonDecrypt);
        decryptButton.setOnClickListener(temp);

        keyButton = (Button)findViewById(R.id.buttonKey);
        keyButton.setOnClickListener(temp);
    }
}
