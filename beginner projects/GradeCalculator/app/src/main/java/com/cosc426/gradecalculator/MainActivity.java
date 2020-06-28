package com.cosc426.gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Grade g;
    private TableLayout tableMain;
    private TableRow tbrow0, tbrow1, tbrow2, tbrow3, tbrow4;
    private TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6;
    private EditText et0, et1, et2;
    private Button calculateButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initializes view
    }

    // Listener interface
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            setupView(); // to update EditText if over max value
            // Parse and set program grade
            try {
                String programString = et0.getText().toString();
                g.setProgram(programString);
                int programInt = Integer.parseInt(programString);
                if(programInt > 200) {
                    et0.setText("200");
                    displayError(0);
                }
            } catch(NumberFormatException e) {
                displayError(1);
                System.out.println(e);
            }
            // Parse and set midterm grade
            try {
                String midtermString = et1.getText().toString();
                g.setMidterm(midtermString);
                int midtermInt = Integer.parseInt(midtermString);
                if(midtermInt > 100) {
                    et0.setText("100");
                    displayError(0);
                }
            } catch(NumberFormatException e) {
                displayError(1);
                System.out.println(e);
            }
            // Parse and set finalExam grade
            try {
                String finalString = et2.getText().toString();
                g.setFinal(finalString);
                int finalInt = Integer.parseInt(finalString);
                if(finalInt > 100) {
                    et0.setText("100");
                    displayError(0);
                }
            } catch(NumberFormatException e) {
                displayError(1);
                System.out.println(e);
            }

            // Get score and letter grade from Grade class, set outputs
            String score = Integer.toString(g.getScore());
            String letter = g.getLetterGrade();
            tv4.setText(score);
            tv6.setText(letter);
        }
    }

    // If e is 0, input is over max value. Otherwise, invalid/empty input.
    private void displayError(int e) {
        String message = "Invalid inputs";
        if(e == 0) {
            message = "Input treated as max value";
        }
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    // Initializes elements and variables
    private void setupView() {
        g = new Grade();
        tableMain = (TableLayout)findViewById(R.id.tableMain);
        // Program
        tbrow0 = (TableRow)findViewById(R.id.row0);
        tv0 = (TextView)findViewById(R.id.tv0);
        et0 = (EditText)findViewById(R.id.et0);
        // Midterm
        tbrow1 = (TableRow)findViewById(R.id.row1);
        tv1 = (TextView)findViewById(R.id.tv1);
        et1 = (EditText)findViewById(R.id.et1);
        // Final
        tbrow2 = (TableRow)findViewById(R.id.row2);
        tv2 = (TextView)findViewById(R.id.tv2);
        et2 = (EditText)findViewById(R.id.et2);
        // Score
        tbrow3 = (TableRow)findViewById(R.id.row3);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        // Grade
        tbrow4 = (TableRow)findViewById(R.id.row4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv6 = (TextView)findViewById(R.id.tv6);

        calculateButton = (Button)findViewById(R.id.calculateButton);
        ButtonHandler temp = new ButtonHandler();
        calculateButton.setOnClickListener(temp);
    }
}
