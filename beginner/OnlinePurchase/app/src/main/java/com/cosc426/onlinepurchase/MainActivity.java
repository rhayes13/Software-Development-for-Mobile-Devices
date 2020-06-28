package com.cosc426.onlinepurchase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ToggleButton;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Calculator c;
    private TextView priceTv, warrantyTv, insuranceTv, deliveryTv, costTv, costOutput;
    private EditText priceEdit;
    private ToggleButton warrantyToggle;
    private Switch insuranceSwitch;
    private Spinner deliverySpinner;
    private Button calculateButton;
    private DecimalFormat df;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView(); // Initializes view
    }

    // Listener interface
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            // Parse and set price
            try {
                String priceString = priceEdit.getText().toString();
                c.setPrice(Float.parseFloat(priceString));
            } catch(NumberFormatException e) {
                System.out.println(e);
            }
            // Check if warranty is toggled; set if yes
            if(warrantyToggle.isChecked()) {
                c.setWarranty(0.1f);
            } else {
                c.setWarranty(0);
            }
            // Check is insurance is switched on, set if yes
            if(insuranceSwitch.isChecked()) {
                c.setInsurance(0.05f);
            } else {
                c.setInsurance(0);
            }
            // Check delivery spinner, set in Calculator class
            try {
               c.setDelivery(deliverySpinner.getSelectedItemPosition());
            } catch(NullPointerException e) {
                c.setDelivery(2);
                System.out.println(e);
            }

            float temp = Float.valueOf(df.format(c.getCost()));
            String finalCost = Float.toString(temp);
            costOutput.setText(finalCost);
        }
    }

    // Initializes elements and variables
    private void setupView() {
        c = new Calculator();
        priceTv = (TextView)findViewById(R.id.priceTv);
        warrantyTv = (TextView)findViewById(R.id.warrantyTv);
        insuranceTv = (TextView)findViewById(R.id.insuranceTv);
        deliveryTv = (TextView)findViewById(R.id.deliveryTv);
        costTv = (TextView)findViewById(R.id.costTv);
        costOutput = (TextView)findViewById(R.id.costOutput);

        priceEdit = (EditText)findViewById(R.id.priceEt);
        warrantyToggle = (ToggleButton)findViewById(R.id.warrantyToggle);
        insuranceSwitch = (Switch)findViewById(R.id.insuranceSwitch);
        deliverySpinner = (Spinner)findViewById(R.id.deliverySpinner);
        df = new DecimalFormat("0.00");

        // Listener interface set for button
        calculateButton = (Button)findViewById(R.id.calculateButton);
        ButtonHandler temp = new ButtonHandler();
        calculateButton.setOnClickListener(temp);
    }
}
