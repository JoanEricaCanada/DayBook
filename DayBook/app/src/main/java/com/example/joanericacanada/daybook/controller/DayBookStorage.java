package com.example.joanericacanada.daybook.controller;

import android.content.Context;

import com.example.joanericacanada.daybook.model.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by joanericacanada on 10/28/15.
 */
public class DayBookStorage {

    //VARIABLES
    private Context context;
    private String filename;

    public DayBookStorage(Context context, String filename){
        this.context = context;
        this.filename = filename;
    }

    // saves entries to JSON
    public void saveEntry(ArrayList<Entry> journal) throws JSONException, IOException{
        JSONArray jArr = new JSONArray();
        for(Entry e : journal)
            jArr.put(e.saveToJson());

        Writer write = null;
        try {
            OutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            write = new OutputStreamWriter(outStream);
            write.write(jArr.toString());
        }catch (FileNotFoundException e) {
            //do nothing
        }finally {
            if(write != null)
                write.close();
        }
    }

    //loads entries from JSON file
    public ArrayList<Entry> loadEntry() throws JSONException, IOException{
        ArrayList<Entry> journal = new ArrayList<>();
        BufferedReader bReader = null;

        try {
            InputStream inStream = context.openFileInput(filename);
            bReader = new BufferedReader(new InputStreamReader(inStream));
            StringBuilder sBuild = new StringBuilder();

            String line;
            while ((line = bReader.readLine()) != null)
                sBuild.append(line);
            JSONArray arr = (JSONArray) new JSONTokener(sBuild.toString()).nextValue();

            for(int count = 0; count < arr.length(); count++)
                journal.add(new Entry(arr.getJSONObject(count)));
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            if(bReader != null)
                bReader.close();
        }
        return journal;
    }
}
