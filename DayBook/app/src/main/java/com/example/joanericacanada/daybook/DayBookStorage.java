package com.example.joanericacanada.daybook;

import android.content.Context;
import android.util.Log;

import com.example.joanericacanada.daybook.Model.EntryModel;

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

/**
 * Created by joanericacanada on 10/28/15.
 */
public class DayBookStorage {


    //VARIABLES
    private Context context;
    private String filename;
    private EntryModel entry;

    public DayBookStorage(Context context, String filename){
        this.context = context;
        this.filename = filename;
    }

    public void saveEntry(EntryModel entry) throws JSONException, IOException{
        JSONArray jArr = new JSONArray();
        jArr.put(entry.saveToJson());

        Writer write = null;
        try {
            if (context == null) {
                Log.e("Context", "null");
            }
            OutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            write = new OutputStreamWriter(outStream);
            write.write(jArr.toString());
            Log.e("JSON", "in");
        }catch (FileNotFoundException e) {
            Log.e("file", "null");
        }finally {
            if(write != null)
                write.close();
        }
    }

    public EntryModel loadEntry() throws JSONException, IOException{
        BufferedReader bReader = null;

        try {
            InputStream inStream = context.openFileInput(filename);
            bReader = new BufferedReader(new InputStreamReader(inStream));
            StringBuilder sBuild = new StringBuilder();

            String line = null;
            while ((line = bReader.readLine()) != null){
                sBuild.append(line);
            }
            JSONArray arr = (JSONArray) new JSONTokener(sBuild.toString()).nextValue();
            Log.e("DBStore", Integer.toString(arr.length()));

            for(int count = 0; count < arr.length(); count++){
                entry = new EntryModel(arr.getJSONObject(count));
            }
        }catch (Exception e){

        }finally {
            if(bReader != null)
                bReader.close();
        }
        return entry;
    }
}
