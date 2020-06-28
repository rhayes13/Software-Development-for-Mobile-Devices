package com.cosc426.fourthapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    TuitionCalculator tc;
    private int val1, val2, val3;
    private TextView creditsTextView; //credits
    private EditText creditsEditText;

    private TextView academicStatusTextView; //academicStatus
    private RadioGroup academicStatusRadioGroup;
    private RadioButton rbGrad;
    private RadioButton rbUnderGrad;
    private RadioButton rbNonDegree;

    private TextView stateStatusTextView; //stateStatus
    private RadioGroup stateStatusRadioGroup;
    private RadioButton rbInState;
    private RadioButton rbOutOfState;

    private TextView optionalExpensesTextView; //optionalExpenses
    private CheckBox dormCheckBox;
    private CheckBox diningCheckBox;
    private CheckBox parkingCheckBox;

    private Button submitButton; //button, generate tuition
    private TextView tuitionTextView;
    private TextView outputTuitionTextView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initialize elements

        // Calculate tuition cost on button click
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = val2 = 1; //val2 initially set to one; or 2 for outOfStateStatus
                val3 = 0; //val3 initialized to 0, accounts for multiple submits

                // Try statement to account for empty string
                try {
                    String credits = creditsEditText.getText().toString();

                    // These statements fix ScrollView from moving to creditsEditText on button click
                    creditsEditText.setTextIsSelectable(false);
                    creditsEditText.setKeyListener(null);
                    creditsEditText.setFocusable(false);

                    tc.setCredits(credits);

                    int tuition = tc.getCredits() * getCheckedAcademic() *
                            getCheckedState() + getOptionalExpenses();

                    String tuitionString = Integer.toString(tuition);
                    outputTuitionTextView.setText("$" + tuitionString);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        });
    }

    // Find which academic status is active, return value to val1
    private int getCheckedAcademic() {
        if(rbGrad.isChecked())       val1 = tc.getGraduateTuition();
        if(rbUnderGrad.isChecked())  val1 = tc.getUndergradTuition();
        if(rbNonDegree.isChecked())  val1 = tc.getNonDegreeTuition();

        return val1;
    }

    // Find which state status is active, return value to val2
    private int getCheckedState() {
        if(rbInState.isChecked())       val2 = tc.getInStateStatus();
        if(rbOutOfState.isChecked())    val2 = tc.getOutOfStateStatus();

        return val2;
    }

    // Find if additional expenses are checked, return summed value (for all selected) to val3
    private int getOptionalExpenses() {
        if(dormCheckBox.isChecked())    val3 += tc.getDormitoryExpense();
        if(diningCheckBox.isChecked())  val3 += tc.getDiningExpense();
        if(parkingCheckBox.isChecked()) val3 += tc.getParkingExpense();

        return val3;
    }

    // Initializes elements and variables
    private void setupView() {
        tc = new TuitionCalculator();
        val1 = val2 = 1;
        val3 = 0;
        creditsTextView = (TextView)findViewById(R.id.labelCredits);
        creditsEditText = (EditText)findViewById(R.id.editCredits);

        academicStatusTextView = (TextView)findViewById(R.id.labelAcademicStatus);
        academicStatusRadioGroup = (RadioGroup)findViewById(R.id.radioAcademicStatus);
        rbGrad = (RadioButton)findViewById(R.id.rbGraduate);
        rbUnderGrad = (RadioButton)findViewById(R.id.rbUndergrad);
        rbNonDegree = (RadioButton)findViewById(R.id.rbNonDegree);

        stateStatusTextView = (TextView)findViewById(R.id.labelStateStatus);
        stateStatusRadioGroup = (RadioGroup)findViewById(R.id.radioStateStatus);
        rbInState = (RadioButton)findViewById(R.id.rbInState);
        rbOutOfState = (RadioButton)findViewById(R.id.rbOutOfState);

        optionalExpensesTextView = (TextView)findViewById(R.id.labelOptionalExpenses);
        dormCheckBox = (CheckBox)findViewById(R.id.cbDormitory);
        diningCheckBox = (CheckBox)findViewById(R.id.cbDining);
        parkingCheckBox = (CheckBox)findViewById(R.id.cbParking);

        submitButton = (Button)findViewById(R.id.buttonSubmit);
        tuitionTextView = (TextView)findViewById(R.id.labelTuition);
        outputTuitionTextView = (TextView)findViewById(R.id.outputTuition);
    }
}
