package com.cosc426.sqnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NOTE_DATABASE_THREE";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "NOTE_TABLE_THREE";

    //5 columns: id, title, contents, date, time
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENTS = "contents";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Command to create table, using keys (no blobs)
        String command = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT,"
                                         + KEY_CONTENTS + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT" + ")";
        db.execSQL(command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion) return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add note to database, returns long ID of the note
    public long addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE, note.getTitle());
        contentValues.put(KEY_CONTENTS, note.getContents());
        contentValues.put(KEY_DATE, note.getDate());
        contentValues.put(KEY_TIME, note.getTime());

        long ID = db.insert(TABLE_NAME, null, contentValues);
        Log.d("inserted ", "ID IS " + ID);
        return ID;
    }

    //Access single note by its ID
    public Note getNote(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor points to current row of DB
        Cursor row = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_TITLE, KEY_CONTENTS, KEY_DATE, KEY_TIME},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if(row != null) {
            row.moveToFirst();
        }

        db.close();
        return new Note(row.getLong(0), row.getString(1),
                row.getString(2), row.getString(3), row.getString(4));
    }

    //Access list of all notes
    public List<Note> getAllNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNotes = new ArrayList<>();

        //Command uses descending order to place new notes at top of list
        String command = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_DATE + " DESC, " + KEY_TIME + " DESC";
        Cursor row = db.rawQuery(command, null);

        if(row != null) {
            if (row.moveToFirst()) {
                do {
                    Note note = new Note();
                    note.setID(row.getLong(0));
                    note.setTitle(row.getString(1));
                    note.setContents(row.getString(2));
                    note.setDate(row.getString(3));
                    note.setTime(row.getString(4));

                    allNotes.add(note);
                } while (row.moveToNext());
            }
        }

        db.close();
        return allNotes;
    }

    //Delete note using its ID
    public void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Update existing note using its ID
    public int editNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE, note.getTitle());
        contentValues.put(KEY_CONTENTS, note.getContents());
        contentValues.put(KEY_DATE, note.getDate());
        contentValues.put(KEY_TIME, note.getTime());

        return db.update(TABLE_NAME, contentValues, KEY_ID + "=?", new String[]{String.valueOf(note.getID())});
    }
}
