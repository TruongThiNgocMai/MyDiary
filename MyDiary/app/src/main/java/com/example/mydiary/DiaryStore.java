package com.example.mydiary;

import androidx.room.PrimaryKey;

public class DiaryStore {

    @PrimaryKey(autoGenerate = true)
    private int id;
    public String title;
    public String content;
    public String datetime;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }
    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getDatetime(){
        return datetime;
    }


    public DiaryStore(){
        this.title = title;
        this.content = content;
        this.datetime = datetime;
    }
}
