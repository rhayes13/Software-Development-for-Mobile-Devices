package com.cosc426.sudokuv2;

import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.graphics.Color;
import android.content.Context;
import android.widget.GridLayout;
import android.util.TypedValue;
import android.text.InputType;

public class AppInterface extends RelativeLayout
{
    private EditText[][] board;
    private int size;
    final int DP;

    public AppInterface(Context context, int size, int width)
    {
        super(context);

        //create interface
        this.size = size;
        DP = (int)(getResources().getDisplayMetrics().density);

        GridLayout grid = new GridLayout(getContext());
        grid.setRowCount(9);
        grid.setColumnCount(9);

        board = new EditText[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                board[i][j] = new EditText(context);
                board[i][j].setBackgroundColor(Color.parseColor("#008577"));
                board[i][j].setTextColor(Color.BLACK);
                board[i][j].setTextSize(5 * DP);
                board[i][j].setGravity(Gravity.CENTER);
                board[i][j].setId(EditText.generateViewId());
                board[i][j].setInputType(InputType.TYPE_CLASS_NUMBER);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = width;
                params.height = width;
                params.rowSpec = GridLayout.spec(i, 1);
                params.columnSpec = GridLayout.spec(j, 1);
                params.topMargin = params.bottomMargin = 1;
                params.leftMargin = params.rightMargin = 1;
                board[i][j].setLayoutParams(params);
                addView(board[i][j]);
            }
        }

        grid.setBackgroundColor(Color.parseColor("#D3D3D3"));
        addView(grid);

    }

    public void drawInitialBoard(int[][] board)
    {
        //draw initial board
    }

    public void setTextChangeHandler(TextWatcher textChangeHandler, int x, int y)
    {
        board[x][y].addTextChangedListener(textChangeHandler);
    }

    public String getInput(int x, int y)
    {
        //return input at x, y
        return board[x][y].getText().toString();
    }

    public void clear(int x, int y)
    {
        //clear input at x, y
        board[x][y].setText("");
    }
}
