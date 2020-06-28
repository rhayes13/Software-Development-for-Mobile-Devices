package com.cosc426.primenumberchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/* DYNAMIC DESIGN */
public class MainActivity extends AppCompatActivity {

    private Model m;
    private Interface i;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m = new Model();
        ButtonHandler buttonHandler = new ButtonHandler();
        i = new Interface(this, buttonHandler);
        setContentView(i);
    }

    public class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            int input = i.getInput(); // Get input from View
            m.setInput(input); // Set input in Model
            String outputString = "Not a prime number";
            if(m.checkPrime()){
                outputString = "Prime number";
            }
            i.setOutput(outputString); // Set output in View
        }
    }
}
