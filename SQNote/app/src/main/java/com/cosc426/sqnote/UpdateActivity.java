package com.cosc426.sqnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private Toolbar toolbar;
    private EditText titleEt, contentsEt;
    private Calendar calendar;
    private String date; //YYYY-MM-DD
    private String time; //HH:MM
    private Long id;
    private String prevTitle, prevContents; //To check if contents changed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dbManager = new DatabaseManager(this);

        //Get extra for ID value, find note by ID
        Intent intent = getIntent();
        id = intent.getExtras().getLong("ID", 0);
        Note note = dbManager.getNote(id);

        //Initialize toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbarMain);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(note.getTitle());

        //Initialize EditTexts
        titleEt = (EditText)findViewById(R.id.titleEt);
        contentsEt = (EditText)findViewById(R.id.contentsEt);
        //Set current title and contents
        titleEt.setText(note.getTitle());
        contentsEt.setText(note.getContents());
        getSupportActionBar().setTitle(note.getTitle());
        //Save these values to check for changes later
        prevTitle = note.getTitle();
        prevContents = note.getContents();

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

        //If save was clicked, create note with current EditText fields, date, & time...addNote to DB
        //If delete was clicked, discard changes if they exist
        if(itemId == R.id.delete) {
            //Confirm delete with dialog box if any changes
            if(!prevTitle.equals(titleEt.getText().toString()) || !prevContents.equals(contentsEt.getText().toString())) {
                showDialogBox();
            } else {
                Toast.makeText(this,"No changes made", Toast.LENGTH_SHORT).show();
                finish(); //Return to PREVIOUS screen
            }
            //Toast.makeText(this,"Note deleted", Toast.LENGTH_SHORT).show();
        } else if(itemId == R.id.save) {
            if(titleEt.getText().toString().equals("") && contentsEt.getText().toString().equals("")) {
                Toast.makeText(this,"Empty note deleted", Toast.LENGTH_SHORT).show();
            } else {
                Note note = new Note(id, titleEt.getText().toString(), contentsEt.getText().toString(), date, time);
                dbManager.editNote(note);
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
            }
            Intent back = new Intent(getApplicationContext(), MainActivity.class); //Return to MAIN screen
            startActivity(back);
        }
        return super.onOptionsItemSelected(item);
    }

    // DialogBox listener for Yes, No, and Cancel
    private class DialogBoxListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int id) {
            switch(id) {
                case DialogInterface.BUTTON_POSITIVE:
                    dbManager.deleteNote(id); //If yes, delete
                    Toast.makeText(getApplicationContext(),"Changes discarded", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    finish();
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss(); //If no, close dialog box and do nothing
            }
        }
    }

    private void showDialogBox() {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);

        dialogBox.setTitle("Are you sure you want to discard changes?");

        DialogBoxListener temp = new DialogBoxListener();
        dialogBox.setPositiveButton("Yes", temp);
        dialogBox.setNegativeButton("No", temp);

        AlertDialog dialog = dialogBox.create();
        dialog.show();
    }
}
