package com.cosc426.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbManager = new DatabaseManager(this);
    }

    //Onclick method to add item to DB via ET fields
    public void add(View v) {
        EditText nameEt = (EditText)findViewById(R.id.nameEt);
        String nameString = nameEt.getText().toString();

        EditText pwEt = (EditText)findViewById(R.id.pwEt);
        String pwString = pwEt.getText().toString();

        Password pw = new Password(nameString, pwString);
        dbManager.insert(pw);
    }

    //Returns to main screen
    public void back(View v) {
        finish();
    }
}
