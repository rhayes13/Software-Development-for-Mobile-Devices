package com.cosc426.sqnote;

public class Note {
    private long ID; //SQ DB uses long values for IDs
    private String title, contents, date, time;

    public Note() {} //default

    //For adding a new note
    public Note(String title, String contents, String date, String time) {
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.time = time;
    }

    //For editing a note based on its ID
    public Note(long id, String title, String contents, String date, String time) {
        this.ID = id;
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.time = time;
    }

    //Getters, setters
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
