package com.example.joanericacanada.daybook.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by joanericacanada on 10/28/15.
 */
public class EntryModel {
    //TAGS
    private static final String JSON_TITLE = "title";
    private static final String JSON_BODY = "body";
    private static final String JSON_DATE = "date";

    //VARIABLES
    private String title, body;
    private Date date;
    //for photo

    public EntryModel(){
        date = new Date();
    }

    public EntryModel(JSONObject json) throws JSONException{
        title = json.getString(JSON_TITLE);
        body = json.getString(JSON_BODY);
        date = new Date(json.getLong(JSON_DATE));
    }

    public JSONObject saveToJson() throws JSONException{
        JSONObject jObject = new JSONObject();
        jObject.put(JSON_TITLE, title);
        jObject.put(JSON_BODY, body);
        jObject.put(JSON_DATE, date.getTime());

        return jObject;
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
