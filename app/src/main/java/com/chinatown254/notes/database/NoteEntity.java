package com.chinatown254.notes.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "notes")
public class NoteEntity {

    @PrimaryKey(autoGenerate = true)
    private  int id;
    @Ignore
    public NoteEntity(Date date, String text) {
        this.date = date;
        this.text = text;
    }

    public NoteEntity(int id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }
    @Ignore
    public NoteEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private Date date;
    private String text;

    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
