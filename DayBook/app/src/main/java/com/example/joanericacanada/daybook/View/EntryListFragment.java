package com.example.joanericacanada.daybook.View;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.joanericacanada.daybook.EntryKeeper;
import com.example.joanericacanada.daybook.Model.EntryModel;
import com.example.joanericacanada.daybook.R;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by joanericacanada on 10/29/15.
 */
public class EntryListFragment extends ListFragment {

    //VARIABLES
    private ArrayList<EntryModel> journal;
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        journal = EntryKeeper.get(getActivity()).getEntries();
        JournalAdapter jAdapter = new JournalAdapter(journal);
        setListAdapter(jAdapter);
    }

    private class JournalAdapter extends ArrayAdapter<EntryModel> {
        public JournalAdapter(ArrayList<EntryModel> journal){
            super(getActivity(), 0, journal);
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent){
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_entry_layout, null);
            }

            EntryModel entry = getItem(pos);
            TextView txtTitle, txtDate;
            txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
            txtTitle.setText(entry.getTitle());
            txtDate = (TextView)convertView.findViewById(R.id.txtDate);
            txtDate.setText(DateFormat.getDateTimeInstance().format(entry.getDate()));

            return convertView;
        }
    }
}
