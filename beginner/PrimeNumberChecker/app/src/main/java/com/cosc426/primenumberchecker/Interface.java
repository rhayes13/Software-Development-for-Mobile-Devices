package com.cosc426.primenumberchecker;

import android.content.Context;
import android.text.InputType;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.TypedValue;
import android.graphics.Color;
import android.view.View;

public class Interface extends RelativeLayout {

    private EditText inputBox;
    private TextView outputText;
    private Button button;

    // Programmatically generate View
    public Interface(Context context, View.OnClickListener buttonHandler) {
        super(context);

        RelativeLayout.LayoutParams params;
        final int DP = (int)(getResources().getDisplayMetrics().density);

        inputBox = new EditText(context);
        inputBox.setId(EditText.generateViewId());
        inputBox.setTextColor(Color.parseColor("#000000"));
        inputBox.setBackgroundColor(Color.parseColor("#D3D3D3"));
        inputBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        inputBox.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        inputBox.setPadding(10*DP, 10*DP, 10*DP, 10*DP);
        inputBox.setHint("0");
        inputBox.setInputType(InputType.TYPE_CLASS_NUMBER);
        params = new RelativeLayout.LayoutParams(0, 0);
        params.width = 260*DP;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        params.topMargin = 20*DP;
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        inputBox.setLayoutParams(params);
        addView(inputBox);

        outputText = new TextView(context);
        outputText.setId(TextView.generateViewId());
        outputText.setTextColor(Color.parseColor("#000000"));
        outputText.setBackgroundColor(Color.parseColor("#008577"));
        outputText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        outputText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        outputText.setPadding(10*DP, 10*DP, 10*DP, 10*DP);
        outputText.setText("");
        params = new RelativeLayout.LayoutParams(0, 0);
        params.width = 260*DP;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        params.topMargin = 20*DP;
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, inputBox.getId());
        outputText.setLayoutParams(params);
        addView(outputText);

        button = new Button(context);
        button.setId(Button.generateViewId());
        button.setTextColor(Color.parseColor("#000000"));
        button.setBackgroundColor(Color.parseColor("#796C6C"));
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        button.setPadding(10*DP, 10*DP, 10*DP, 10*DP);
        button.setText("SUBMIT");
        params = new RelativeLayout.LayoutParams(0, 0);
        params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        params.topMargin = 20*DP;
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, outputText.getId());
        button.setLayoutParams(params);
        button.setOnClickListener(buttonHandler);
        addView(button);
    }

    public int getInput() {
        int input = 0;
        try {
            String inputString = inputBox.getText().toString();
            input = Integer.parseInt(inputString);
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
        return input;
    }

    public void setOutput(String s) {
        outputText.setText(s);
    }
}
