package com.cosc426.slidingpuzzle;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.content.Context;
import android.widget.GridLayout;
import android.util.TypedValue;

public class AppInterface extends RelativeLayout
{
    private int size = 3;
    private TextView[][] board;
    private TextView output;

    public AppInterface(Context context, int size, int width)
    {
        super(context);
        final int DP = (int)(getResources().getDisplayMetrics().density);

        // Margin for main layout
        RelativeLayout.LayoutParams params0 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params0.topMargin= 40*DP;
        setLayoutParams(params0);

        //create first board in a grid, uses new linear layout to create rows
        // Linear layout to help align rows
        LinearLayout linear = new LinearLayout(getContext());
        linear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linear.setOrientation(LinearLayout.VERTICAL);


        board = new TextView[size][size];
        for(int i = 0; i < size; i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            row.setGravity(Gravity.CENTER_HORIZONTAL);

            for(int j = 0; j < size; j++) {
                board[i][j] = new TextView(context);
                board[i][j].setId(TextView.generateViewId());
                board[i][j].setBackgroundColor(Color.parseColor("#5EA2B5"));
                board[i][j].setTextAlignment(RelativeLayout.TEXT_ALIGNMENT_CENTER);
                board[i][j].setTextColor(Color.BLACK);
                board[i][j].setGravity(Gravity.CENTER);
                board[i][j].setTextSize(20*DP);
                board[i][j].setId(TextView.generateViewId());
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = width;
                params.height = width;
                params.rowSpec = GridLayout.spec(i, 1);
                params.columnSpec = GridLayout.spec(j, 1);
                params.topMargin = params.bottomMargin = 1*DP;
                params.leftMargin = params.rightMargin = 1*DP;
                board[i][j].setLayoutParams(params);
                row.addView(board[i][j]); // add item to row
            }
            linear.addView(row); // add row to linear layout
        }
        addView(linear);

        //Output text view
        output = new TextView(context);
        output.setTextColor(Color.BLACK);
        output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        output.setPadding(10*DP, 10*DP, 10*DP, 10*DP);
        output.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(0, 0);
        params2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params2.width = LayoutParams.WRAP_CONTENT;
        params2.height = LayoutParams.WRAP_CONTENT;
        params2.topMargin = 333 * DP;
        params2.bottomMargin = 50*DP;
        output.setLayoutParams(params2);
        addView(output);
    }

    public void drawBoard(char[][] b)
    {
        //draw current board
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                board[i][j].setText(String.valueOf(b[i][j]));
            }
        }
    }

    public void gameWon() {
        output.setText("You won!");
        output.setBackgroundColor(Color.parseColor("#D3D3D3"));
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                board[i][j].setBackgroundColor(Color.parseColor("#990000"));
            }
        }
    }
}
