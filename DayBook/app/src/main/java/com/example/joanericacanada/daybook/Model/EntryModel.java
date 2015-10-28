package com.example.joanericacanada.daybook.Model;

import java.util.Date;

/**
 * Created by joanericacanada on 10/28/15.
 */
public class EntryModel {

    private String title, body;
    private Date date;
    //for photo

    public EntryModel(String title, String body, Date date){
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
