package com.cosc426.passwordmanager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbManager = new DatabaseManager(this);

        updateView(); //initializes view
    }

    protected void onStart() {
        super.onStart();
        updateView(); //updates view
    }

    private void updateView() {
        LinkedList<Password> list = dbManager.all(); //get LL of passwords

        //Setup graphics properties and layoutParams
        if(list.size() > 0) {
            //graphics
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int buttonWidth = size.x/2;
            int DP = (int)(getResources().getDisplayMetrics().density);
            int rows = (list.size() + 1)/2;
            int columns = 2;

            //params
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(rows);
            grid.setColumnCount(columns);
            ScrollView.LayoutParams params = new ScrollView.LayoutParams(0, 0);
            params.width = columns*buttonWidth;
            params.height = rows*buttonWidth;
            grid.setLayoutParams(params);

            //buttons to store data in table
            Button[] buttons = new Button[list.size()];
            for(int i = 0; i < list.size(); i++) {
                Password pw = list.get(i);

                buttons[i] = new Button(this);
                buttons[i].setText(pw.getName() + '\n' + pw.getPassword());
                buttons[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                buttons[i].setTextColor(Color.parseColor("#000000"));
                buttons[i].setBackgroundColor(Color.parseColor("#AEA6A6"));
                buttons[i].setPadding(10*DP, 10*DP, 10*DP, 10*DP);
                buttons[i].setGravity(Gravity.CENTER);

                GridLayout.LayoutParams params1 = new GridLayout.LayoutParams();
                params1.width = buttonWidth;
                params1.height = buttonWidth;
                params1.rowSpec = GridLayout.spec(i/columns, 1);
                params1.columnSpec = GridLayout.spec(i%columns, 1);
                params1.topMargin = params1.bottomMargin = 2;
                params1.leftMargin = params1.rightMargin = 2;
                buttons[i].setLayoutParams(params1);

                grid.addView(buttons[i]);
            }

            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
            scroll.addView(grid);
        }
        else {
            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();

        //find which menu item was selected, start that activity
        if(id == R.id.add) {
            Intent addActivity = new Intent(this, AddActivity.class);
            startActivity(addActivity);
        } else if(id == R.id.delete) {
            Intent deleteActivity = new Intent(this, DeleteActivity.class);
            startActivity(deleteActivity);
        } else if(id == R.id.update) {
            Intent updateActivity = new Intent(this, UpdateActivity.class);
            startActivity(updateActivity);
        }


        return true;
    }
}
