package com.example.joanericacanada.daybook.Controller;

import android.content.Context;
import android.util.Log;

import com.example.joanericacanada.daybook.Model.EntryModel;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by joanericacanada on 10/29/15.
 */
public class EntryKeeper {
    //TAGS
    private static final String FILENAME = "daybook.json";

    //VARIABLES
    private Context contextApp;
    private static EntryKeeper entryKeeper;
    private DayBookStorage book;
    private ArrayList<EntryModel> journal;

    private EntryKeeper(Context contextApp){
        this.contextApp = contextApp;
        book = new DayBookStorage(contextApp, FILENAME);

        try{
            journal = book.loadEntry();
            Log.e("keeper", "loaded");
        }catch (Exception e){
            journal = new ArrayList<>();
            Log.e("keeper", "not loaded");
        }
    }

    public void newEntry(EntryModel e){
        journal.add(e);
    }

    public static EntryKeeper get(Context c){
        if(entryKeeper == null) {
            entryKeeper = new EntryKeeper(c.getApplicationContext());
            Log.e("ekeeper", "null");
        }
        return entryKeeper;
    }

    public ArrayList<EntryModel> getEntries(){
        return journal;
    }

    public EntryModel getEntry(UUID id){
        for(EntryModel e : journal){
            if(e.getId().equals(id))
                return e;
        }
        return null;
    }

    public boolean saveEntries(){
        try{
            book.saveEntry(journal);
            Log.e("eKeeper", "saved");
            return true;
        }catch (Exception e){
            Log.e("eKeeper", "not saved");
            return false;
        }
    }
}
