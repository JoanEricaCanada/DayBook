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
import java.util.Date;

public class EntryActivity extends FragmentActivity {
    //TAGS

    //WIDGETS
    private TextView txtDate;
    private EditText edtTitle, edtBody;

    //VARIABLES
    DayBookStorage dbs;
    EntryModel entry = new EntryModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_entry_layout);

        dbs = new DayBookStorage(getApplicationContext(), "daybook.json");

        String date = DateFormat.getDateTimeInstance().format(new Date());
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtDate.setText(date);

        edtTitle = (EditText) findViewById(R.id.edtTitle);

        edtBody = (EditText) findViewById(R.id.edtEntry);
    }

    @Override
    protected void onPause() {
        super.onPause();
        entry.setBody(edtBody.getText().toString());
        entry.setTitle(edtTitle.getText().toString());
        entry.setDate(entry.getDate());

        try {
            dbs.saveEntry(entry);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
