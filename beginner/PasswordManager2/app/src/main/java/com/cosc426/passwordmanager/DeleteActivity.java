package com.cosc426.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import java.lang.reflect.Type;
import java.util.LinkedList;

public class DeleteActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    private void updateView() {
        LinkedList<Password> list = dbManager.all();

        if(list.size() > 0) {
            RadioGroup group = new RadioGroup(this);
            ScrollView.LayoutParams params = new ScrollView.LayoutParams(0, 0);
            params.width = ScrollView.LayoutParams.WRAP_CONTENT;
            params.height = ScrollView.LayoutParams.WRAP_CONTENT;
            group.setLayoutParams(params);

            //Radio buttons with onclick listener to delete; deleted on select
            RadioButton[] buttons = new RadioButton[list.size()];

            for(int i = 0; i < list.size(); i++) {
                Password pw = list.get(i);
                buttons[i] = new RadioButton(this);
                buttons[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                buttons[i].setTextColor(Color.parseColor("#000000"));
                buttons[i].setText(pw.getName().toUpperCase() + " " + pw.getPassword());

                RadioButtonHandler temp = new RadioButtonHandler(pw.getName());
                buttons[i].setOnClickListener(temp);

                group.addView(buttons[i]);
            }

            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
            scroll.addView(group);
        } else {
            ScrollView scroll = (ScrollView)findViewById(R.id.scroll);
            scroll.removeAllViewsInLayout();
        }
    }

    private class RadioButtonHandler implements View.OnClickListener {
        private String name;

        public RadioButtonHandler(String name) {
            this.name = name;
        }

        //delete item onClick and updateView
        public void onClick(View view) {
            dbManager.delete(name);
            updateView();
        }
    }

    //Return to main screen
    public void back(View view) {
        finish();
    }
}
