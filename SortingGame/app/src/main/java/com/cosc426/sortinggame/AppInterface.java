package com.cosc426.sortinggame;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class AppInterface extends RelativeLayout {
    private final int SIZE = 10;
    private int windowView;
    private TextView[] nums;
    private TextView output;
    private Random r;

    public AppInterface(Context context) {
        super(context);
        nums = new TextView[SIZE];
        r = new Random();
        windowView = r.nextInt(9) + 1;

        final int DP = (int)(getResources().getDisplayMetrics().density);

        //Generate initial TextView
        for(int i = 0; i < SIZE; i++) {
            nums[i] = new TextView(context);
            nums[i].setId(TextView.generateViewId());
            nums[i].setBackgroundColor(Color.parseColor("#009900"));
            nums[i].setTextColor(Color.BLACK);
            nums[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
            nums[i].setGravity(Gravity.CENTER);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(0, 0);
            params.topMargin = 1;
            if (i == 0) params.topMargin = 15 * DP;
            if (i > 0) params.addRule(RelativeLayout.BELOW, nums[i-1].getId());
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.width = 70 * DP;
            params.height = LayoutParams.WRAP_CONTENT;
            nums[i].setLayoutParams(params);
            addView(nums[i]);
        }

        //Output text view
        output = new TextView(context);
        output.setBackgroundColor(Color.parseColor("#D3D3D3"));
        output.setTextColor(Color.BLACK);
        output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        output.setPadding(10*DP, 10*DP, 10*DP, 10*DP);
        output.setGravity(Gravity.CENTER);
        output.setText("Swaps left: 45");
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(0, 0);
        params2.addRule(RelativeLayout.BELOW, nums[9].getId());
        params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params2.width = LayoutParams.WRAP_CONTENT;
        params2.height = LayoutParams.WRAP_CONTENT;
        params2.topMargin = 20 * DP;
        output.setLayoutParams(params2);
        addView(output);
    }

    //Show current array
    public void showCurrent(int[] current) {
        for(int i = 0; i < SIZE; i++) {
            nums[i].setText(Integer.toString(current[i]));
        }
    }

    public int getWindowView() {
        return windowView;
    }

    public void updateTextView(int swaps) {
        output.setText("Swaps left: " + swaps);
    }

    public void lossMessage() {
        output.setText("No swaps left! You lose!");
    }

    public void winMessage(int swaps) {
        output.setText("You won with " + swaps + " swaps left!");
    }

    //Update window view on click
    public void updateWindow() {
        //Return first case to default color
        windowView++;
        if(windowView > 8) windowView = 0;
        if(windowView == 0){
            nums[windowView].setBackgroundColor(Color.parseColor("#000099"));
            nums[windowView+1].setBackgroundColor(Color.parseColor("#000099"));
            nums[8].setBackgroundColor(Color.parseColor("#009900"));
            nums[9].setBackgroundColor(Color.parseColor("#009900"));
        } else {
            nums[windowView-1].setBackgroundColor(Color.parseColor("#009900"));
            nums[windowView].setBackgroundColor(Color.parseColor("#000099"));
            nums[windowView+1].setBackgroundColor(Color.parseColor("#000099"));
        }
    }

}
