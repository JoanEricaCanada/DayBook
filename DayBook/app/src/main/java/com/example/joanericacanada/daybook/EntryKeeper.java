package com.example.joanericacanada.daybook;

import android.content.Context;

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
    Context contextApp;
    private static EntryKeeper entryKeeper;
    private DayBookStorage book;
    ArrayList<EntryModel> journal = new ArrayList<>();

    private EntryKeeper(Context contextApp){
        this.contextApp = contextApp;
        book = new DayBookStorage(contextApp, FILENAME);

        try{
            journal = book.loadEntry();
        }catch (Exception e){
            journal = new ArrayList<>();
        }
    }

    public void newEntry(EntryModel e){
        journal.add(e);
    }

    public static EntryKeeper get(Context c){
        if(entryKeeper == null)
            entryKeeper = new EntryKeeper(c.getApplicationContext());
        return entryKeeper;
    }

    public ArrayList<EntryModel> getEntries(){
        return journal;
    }

    public EntryModel getEntry(UUID id){
        for(EntryModel e : journal){
            if(e.getId() == id)
                return e;
        }
        return null;
    }
}
