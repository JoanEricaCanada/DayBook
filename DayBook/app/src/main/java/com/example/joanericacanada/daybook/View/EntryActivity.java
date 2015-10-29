package com.example.joanericacanada.daybook.View;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.joanericacanada.daybook.DayBookStorage;
import com.example.joanericacanada.daybook.Model.EntryModel;
import com.example.joanericacanada.daybook.R;

import org.json.JSONException;

import java.io.IOException;
import java.text.DateFormat;

public class EntryActivity extends FragmentActivity {
    //TAGS

    //WIDGETS
    private TextView txtDate;
    private EditText edtTitle, edtBody;

    //VARIABLES
    DayBookStorage dbs;
    EntryModel entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_entry_layout);

        dbs = new DayBookStorage(this, "daybook.json");

        try {
            entry = dbs.loadEntry();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(entry == null)
            entry = new EntryModel();

        String currentdate = DateFormat.getDateTimeInstance().format(entry.getDate());
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtDate.setText(currentdate);

        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtTitle.setText(entry.getTitle());

        edtBody = (EditText) findViewById(R.id.edtEntry);
        edtBody.setText(entry.getBody());
    }

    @Override
    protected void onPause() {
        super.onPause();
        entry.setBody(edtBody.getText().toString());
        entry.setTitle(edtTitle.getText().toString());

        try {
            dbs.saveEntry(entry);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
