package com.cosc426.sqnote;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Contents extends AppCompatActivity {

    private DatabaseManager dbManager;
    private TextView noteContents;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        noteContents = (TextView)findViewById(R.id.noteContents);

        //Get extra for ID value
        Intent intent = getIntent();
        Long id = intent.getExtras().getLong("ID", 0);

        dbManager = new DatabaseManager(this);
        note = dbManager.getNote(id); //Access note by id
        getSupportActionBar().setTitle(note.getTitle()); //Set title of note
        noteContents.setText(note.getContents()); //Set contents of note
        noteContents.setMovementMethod(new ScrollingMovementMethod()); //ScrollView

        //Delete note from database using its id on action button click
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showDialogBox();
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adds items to the action bar
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        //If save was clicked, create note with EditText fields, date, & time...addNote to DB
        if(itemId == R.id.edit) {
            //Open edit activity
            Intent updateActivity = new Intent(this, UpdateActivity.class);
            updateActivity.putExtra("ID", note.getID());
            startActivity(updateActivity);
            //finish(); //Return to main screen
            //Toast.makeText(this,"EDIT NOTE", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //DialogBox listener for Yes, No, and Cancel
    private class DialogBoxListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int id) {
            switch(id) {
                case DialogInterface.BUTTON_POSITIVE:
                    dbManager.deleteNote(note.getID()); //If yes, delete
                    Toast.makeText(getApplicationContext(),"Note deleted", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    finish();
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss(); //If no, close dialog box and do nothing
            }
        }
    }

    private void showDialogBox() {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);

        dialogBox.setTitle("Are you sure you want to delete?");
        //dialogBox.setMessage("Title: " + note.getTitle());

        DialogBoxListener temp = new DialogBoxListener();
        dialogBox.setPositiveButton("Yes", temp);
        dialogBox.setNegativeButton("No", temp);

        AlertDialog dialog = dialogBox.create();
        dialog.show();
    }
}
