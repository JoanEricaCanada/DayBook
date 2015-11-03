package com.example.joanericacanada.daybook.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joanericacanada.daybook.R;
import com.example.joanericacanada.daybook.controller.EntryKeeper;
import com.example.joanericacanada.daybook.model.Entry;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by joanericacanada on 10/29/15.
 */
public class EntryListFragment extends ListFragment{

    //VARIABLES
    private ArrayList<Entry> journal, result;
    private View header;

    @Override
    public void onResume(){
        super.onResume();
        ((journalAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        result = journal = EntryKeeper.get(getActivity()).getEntries();
        //jAdapter = new journalAdapter(journal);

        //default sort: by date
        Collections.sort(result, new Comparator<Entry>() {
            @Override
            public int compare(Entry lhs, Entry rhs) {
                return rhs.getDate().compareTo(lhs.getDate());
            }
        });

        Toast.makeText(getContext(), "Welcome to DayBook!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        header = getActivity().getLayoutInflater().inflate(R.layout.entry_list_fragment, null);

        Spinner spinner = (Spinner)header.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.filter) );
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filter(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setAdapter(adapter);

        this.getListView().addHeaderView(header);
        setListAdapter(new journalAdapter(result));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((journalAdapter)getListAdapter()).notifyDataSetChanged();
        //((journalAdapter)getListAdapter()).getFilter().filter(DateFormat.getDateInstance().format(new Date()));
    }

    @Override
    public void onListItemClick(ListView l, View v,int pos, long id){
        Entry e = ((journalAdapter)getListAdapter()).getItem(pos-1);

        Intent i = new Intent(getActivity(), EntryPagerActivity.class);
        i.putExtra(SelectedEntryFragment.ENTRY_ID, e.getId());
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
                Entry entry = new Entry();
                EntryKeeper.get(getActivity()).newEntry(entry);

                Intent intent = new Intent(getActivity(), EntryActivity.class);
                intent.putExtra(EntryFragment.ENTRY_ID, entry.getId());
                startActivity(intent);
                return true;
            case R.id.change_password:
                Intent intentPassword = new Intent(getActivity(), PasswordActivity.class);
                intentPassword.putExtra(PasswordActivity.CHANGE_PASSWORD, true);
                startActivity(intentPassword);
                return true;
            case R.id.sort:
                sortList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortList(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.sort_text).setItems(R.array.sorter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0: //default sort: by date
                        Collections.sort(result, new Comparator<Entry>() {
                            @Override
                            public int compare(Entry lhs, Entry rhs) {
                                return rhs.getDate().compareTo(lhs.getDate());
                            }
                        });
                        ((journalAdapter)getListAdapter()).notifyDataSetChanged();
                        break;
                    case 1: //default sort: by title
                        Collections.sort(result, new Comparator<Entry>() {
                            @Override
                            public int compare(Entry lhs, Entry rhs) {
                                return lhs.getTitle().compareTo(rhs.getTitle());
                            }
                        });
                        ((journalAdapter)getListAdapter()).notifyDataSetChanged();
                        break;
                }
            }
        }).show();
    }

    private void filter(int choice){
        result = new ArrayList<>();
        Calendar recent= Calendar.getInstance();
        Calendar entryDate= Calendar.getInstance();

        recent.setTime(new Date());

        switch (choice){
            case 0: //No Filter
                if(result != null)
                    result.addAll(journal);
                break;
            case 1: //Filter by Month
                for (Entry e : journal){
                    entryDate.setTime(e.getDate());
                    if (entryDate.get(Calendar.YEAR) == recent.get(Calendar.YEAR)
                            & entryDate.get(Calendar.MONTH) == recent.get(Calendar.MONTH)) {
                        result.add(e);
                    }
                }
                break;
        }
        setListAdapter(new journalAdapter(result));
        ((journalAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class journalAdapter extends ArrayAdapter<Entry>{
        public journalAdapter(ArrayList<Entry> journal){
            super(getActivity(), R.layout.entry_list_fragment, journal);
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent){
            if (convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_entry_layout, null);
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
}
