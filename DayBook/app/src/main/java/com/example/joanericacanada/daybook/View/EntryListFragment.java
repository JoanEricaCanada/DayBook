package com.example.joanericacanada.daybook.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        setHasOptionsMenu(true);
        journal = EntryKeeper.get(getActivity()).getEntries();
        journalAdapter jAdapter = new journalAdapter(journal);
        setListAdapter(jAdapter);
    }
    @Override
    public void onResume(){
        super.onResume();
        ((journalAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v,int pos, long id){
        EntryModel e = ((journalAdapter)getListAdapter()).getItem(pos);

        Intent i = new Intent(getActivity(), EntryPagerActivity.class);
        i.putExtra(EntryFragment.ENTRY_ID, e.getId());
        startActivity(i);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_entry, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_entry:
                EntryModel entry = new EntryModel();
                EntryKeeper.get(getActivity()).newEntry(entry);

                Intent intent = new Intent(getActivity(), EntryPagerActivity.class);
                intent.putExtra(EntryFragment.ENTRY_ID, entry.getId());
                startActivityForResult(intent, 0);
                //startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class journalAdapter extends ArrayAdapter<EntryModel> {
        public journalAdapter(ArrayList<EntryModel> journal){
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
