package com.cosc426.sqnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private Toolbar toolbar;
    private EditText titleEt, contentsEt;
    private Calendar calendar;
    private String date; //YYYY-MM-DD
    private String time; //HH:MM

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbManager = new DatabaseManager(this);

        //Initialize toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbarMain);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Note");

        //Initialize EditTexts
        titleEt = (EditText)findViewById(R.id.titleEt);
        contentsEt = (EditText)findViewById(R.id.contentsEt);

        //Toolbar label matches title on text change
        titleEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Find current date and time using calender (YYYY-MM-DD), (HH:MM)
        calendar = Calendar.getInstance();
        date = calendar.get(Calendar.YEAR) + "-"
                + (calendar.get(Calendar.MONTH) + 1) + "-" //Month index starts from 0
                + calendar.get(Calendar.DAY_OF_MONTH);

        //TODO: am/pm
        time = addZero(calendar.get(Calendar.HOUR))+ ":" + addZero(calendar.get(Calendar.MINUTE));


        //Log.d( "calendar", "DATE AND TIME" + date + " " + time);
    }

    //If hour or minute have a single digit, concatenate a 0 to front
    private String addZero(int num){
        if(num < 10) {
            return "0" + num;
        }
        return String.valueOf(num);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adds items to the action bar
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        //If save was clicked, create note with EditText fields, date, & time...addNote to DB
        if(itemId == R.id.delete) {
            //If deleted from this screen, simply don't save note and return to prev screen
            finish();
            //Toast.makeText(this,"DELETE PRESSED", Toast.LENGTH_SHORT).show();
        } else if(itemId == R.id.save) {
            if(titleEt.getText().toString().equals("") && contentsEt.getText().toString().equals("")) {
                Toast.makeText(this,"Empty note not created", Toast.LENGTH_SHORT).show();
            } else {
                Note note = new Note(titleEt.getText().toString(), contentsEt.getText().toString(), date, time);
                dbManager.addNote(note);
                Toast.makeText(this,"Note created", Toast.LENGTH_SHORT).show();
            }
            finish(); //Return to main screen
            //Toast.makeText(this,"SAVE PRESSED", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
