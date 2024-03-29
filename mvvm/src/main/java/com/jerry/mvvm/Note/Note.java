package com.jerry.mvvm.Note;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")

class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private  String description;

    private int priority;

    public Note(String title, String description, int priority){
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
