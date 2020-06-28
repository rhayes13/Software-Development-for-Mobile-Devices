package com.cosc426.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Generator g;
    private int actual;
    private TextView tv1, tv2, guessTv;
    private EditText guessEt;
    private Button submitButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initialize view, set actual number
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            // Set guess
            String guessString = guessEt.getText().toString();
            try {
                g.setGuess(Integer.parseInt(guessString));
            } catch(NumberFormatException e) {
                displayError();
                System.out.println(e);
            }

            // Check guess compared to actual
            if(g.getGuess() > actual) {
                guessTv.setText(g.getGuess() + " is too high!");
                g.decreaseChances();
                tv2.setText("You have " + g.getChancesLeft() + " chances left");
            }

            if(g.getGuess() < actual) {
                guessTv.setText(g.getGuess() + " is too low!");
                g.decreaseChances();
                tv2.setText("You have " + g.getChancesLeft() + " chances left");
            }

            // If user guessed actual number, call WIN dialog
            if(g.getGuess() == actual) {
                showDialogBox(0);
            }

            // If user is out of chances, call LOSE dialog
            if(g.getChancesLeft() <= 0) {
                showDialogBox(1);
            }

            // Clear EditText each button press
            guessEt.setText("");
        }
    }

    // DialogBox listener for Yes, No, and Cancel
    private class DialogBoxListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int id) {
            switch(id) {
                case DialogInterface.BUTTON_POSITIVE:
                    setupView(); // Reset view; play again
                case DialogInterface.BUTTON_NEGATIVE:
                    MainActivity.this.finish(); // Exit app
                case DialogInterface.BUTTON_NEUTRAL:
                    dialog.dismiss(); // Close dialog box
            }
        }
    }

    private void showDialogBox(int condition) {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);
        if(condition == 0) {
            dialogBox.setTitle("You win!");
            dialogBox.setMessage("Want to play again?");
        }
        if(condition == 1) {
            dialogBox.setTitle("You lose!");
            dialogBox.setMessage("Want to play again?");
        }

        DialogBoxListener temp = new DialogBoxListener();
        dialogBox.setPositiveButton("Yes", temp);
        dialogBox.setNegativeButton("No", temp);
        dialogBox.setNeutralButton("Cancel", temp);

        AlertDialog dialog = dialogBox.create();
        dialog.show();
    }

    // Display Toast message for empty input
    private void displayError() {
        String message = "You didn't enter an input!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    // Initialize elements
    private void setupView() {
        g = new Generator();
        g.setActual();
        actual = g.getActual();
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText("You have 8 chances");
        guessTv = (TextView) findViewById(R.id.guessTv);
        guessTv.setText("Enter your guess:");
        guessEt = (EditText) findViewById(R.id.inputEt);
        guessEt.setText("");
        submitButton = (Button) findViewById(R.id.submitButton);
        ButtonHandler temp = new ButtonHandler();
        submitButton.setOnClickListener(temp);
    }
}
