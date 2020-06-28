package com.cosc426.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PASSWORD_DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "PASSWORD_TABLE"; //name and password

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Call command to create table
    public void onCreate(SQLiteDatabase db) {
        String command = "create table " + TABLE_NAME + "(" + "NAME blob, " + "PASSWORD blob)";
        db.execSQL(command);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    //Add new item to database
    public void insert(Password pw) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("NAME", pw.getName());
        row.put("PASSWORD", pw.getPassword());
        db.insert(TABLE_NAME, null, row);

        db.close();
    }

    //Remove item from database
    public void delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "NAME = ?", new String[]{name});

        db.close();
    }

    //Update item in database
    public void update(Password pw) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("NAME", pw.getName());
        row.put("PASSWORD", pw.getPassword());
        db.update(TABLE_NAME, row, "NAME = ?", new String[]{pw.getName()});

        db.close();
    }

    //Returns a LL of all Password objects
    public LinkedList<Password> all() {
        SQLiteDatabase db = getWritableDatabase();
        LinkedList<Password> list = new LinkedList<Password>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"NAME", "PASSWORD"}, null, null, null, null, null);

        while(cursor.moveToNext()) {
            String name = cursor.getString(0);
            String password = cursor.getString(1);
            Password pw = new Password(name, password);
            list.addLast(pw);
        }

        cursor.close();
        db.close();

        return list;
    }

}
