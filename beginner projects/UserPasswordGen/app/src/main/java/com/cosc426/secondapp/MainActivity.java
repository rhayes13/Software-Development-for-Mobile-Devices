package com.cosc426.secondapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView dateOfBirthTextView;
    private TextView userID;
    private TextView userPassword;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText dateOfBirthEditText;
    private TextView outputUserID;
    private TextView outputUserPassword;
    private Button generateButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); /* Initialize elements */

        /* Call generate() function on button click; if any string is empty, catch error */
        generateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    generate();
                } catch(RuntimeException e) {
                    System.out.println(e); // Can be changed to Toast
                }
            }
        });
    }

    /* Reads TextView into Strings, calls Inputs class to set output for user ID and password */
    private void generate() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String dob = dateOfBirthEditText.getText().toString();

        Inputs i = new Inputs(firstName, lastName, dob);

        outputUserID.setText(i.getUserID(firstName, lastName, dob));
        outputUserPassword.setText(i.getUserPassword(firstName, lastName, dob));
    }

    /* Initializes elements and variable */
    private void setupView() {
        firstNameTextView = (TextView)findViewById(R.id.labelFirstName);
        lastNameTextView = (TextView)findViewById(R.id.labelLastName);
        dateOfBirthTextView = (TextView)findViewById(R.id.labelDateOfBirth);
        userID = (TextView)findViewById(R.id.labelUserID);
        userPassword = (TextView)findViewById(R.id.labelUserPassword);
        firstNameEditText = (EditText)findViewById(R.id.editFirstName);
        lastNameEditText = (EditText)findViewById(R.id.editLastName);
        dateOfBirthEditText = (EditText)findViewById(R.id.editDOB);
        outputUserID = (TextView)findViewById(R.id.outputUserID);
        outputUserPassword = (TextView)findViewById(R.id.outputUserPassword);
        generateButton = (Button)findViewById(R.id.buttonGenerate);
    }
}
