package com.example.joanericacanada.daybook.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.joanericacanada.daybook.R;
import com.example.joanericacanada.daybook.model.Entry;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by joanericacanada on 11/6/15.
 */
public class JournalAdapter extends ArrayAdapter<Entry> {
        public JournalAdapter(Context context, ArrayList<Entry> journal){
            super(context, R.layout.list_entry_layout, journal);
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent){
            if (convertView == null){
                convertView = View.inflate(getContext(), R.layout.list_entry_layout, null);
            }

            Entry entry = getItem(pos);
            TextView txtTitle, txtDate;
            txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
            txtTitle.setText(entry.getTitle());
            txtDate = (TextView)convertView.findViewById(R.id.txtDate);
            txtDate.setText(DateFormat.getDateTimeInstance().format(entry.getDate()));

            return convertView;
        }
}
