package com.example.joanericacanada.daybook.controller;

import android.content.Context;
import android.util.Log;

import com.example.joanericacanada.daybook.model.Entry;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by joanericacanada on 10/29/15.
 */
public class EntryKeeper {
    //TAGS
    private static final String FILENAME = "daybook.json";
    private static final String TAG = "EntryKeeper";

    private static EntryKeeper entryKeeper;
    private DayBookStorage book;
    private ArrayList<Entry> journal;

    private EntryKeeper(Context context){
        //Context contextApp = context;
        book = new DayBookStorage(context, FILENAME);

        try{
            journal = book.loadEntry();
            Log.e("keeper", "loaded");
        }catch (Exception e){
            journal = new ArrayList<>();
            Log.e("keeper", "not loaded");
        }
    }

    public void newEntry(Entry e){
        journal.add(e);
    }

    public static EntryKeeper get(Context c){
        if(entryKeeper == null) {
            entryKeeper = new EntryKeeper(c.getApplicationContext());
            Log.e(TAG, "null");
        }
        return entryKeeper;
    }

    public ArrayList<Entry> getEntries(){
        return journal;
    }

    public Entry getEntry(UUID id){
        for(Entry e : journal){
            if(e.getId().equals(id))
                return e;
        }
        return null;
    }

    public void saveEntries(){
        try{
            book.saveEntry(journal);
            Log.e(TAG, "saved");
        }catch (Exception e){
            Log.e(TAG, "not saved");
        }
    }
}
