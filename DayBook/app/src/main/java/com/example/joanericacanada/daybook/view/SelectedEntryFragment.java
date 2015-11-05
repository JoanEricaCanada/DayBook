package com.example.joanericacanada.daybook.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joanericacanada.daybook.R;
import com.example.joanericacanada.daybook.controller.EntryKeeper;
import com.example.joanericacanada.daybook.model.Entry;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by joanericacanada on 10/30/15.
 */
public class SelectedEntryFragment extends Fragment {
    //TAGS
    public static final String ENTRY_ID = "id";

    //WIDGETS
    private TextView mTxtTitle;
    private TextView mTxtBody;
    private Entry entry;

    public static SelectedEntryFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(ENTRY_ID, id);

        SelectedEntryFragment fragment = new SelectedEntryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID id = (UUID)getArguments().getSerializable(ENTRY_ID);
        entry = EntryKeeper.get(getActivity()).getEntry(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_entry_layout, parent, false);

        mTxtTitle = (TextView) view.findViewById(R.id.txtTitle);
        mTxtTitle.setText(entry.getTitle());

        String currentDate = DateFormat.getDateTimeInstance().format(entry.getDate());
        TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtDate.setText(currentDate);

        mTxtBody = (TextView) view.findViewById(R.id.txtBody);
        mTxtBody.setText(entry.getBody());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.selected_entry_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_entry:
                Intent editIntent = new Intent(getActivity(), EntryActivity.class);
                editIntent.putExtra(EntryFragment.ENTRY_ID, entry.getId());
                startActivityForResult(editIntent, 0);
                return true;
            case R.id.delete_entry:
                ArrayList<Entry> journal = EntryKeeper.get(getActivity()).getEntries();
                journal.remove(entry);
                getActivity().finish();
                return true;
            case R.id.export_entry:
                Intent exportIntent = new Intent(Intent.ACTION_SEND);
                exportIntent.setType("text/plain");
                exportIntent.putExtra(Intent.EXTRA_TEXT, exportEntry());
                exportIntent.putExtra(Intent.EXTRA_SUBJECT,entry.getTitle());
                exportIntent = Intent.createChooser(exportIntent, getString(R.string.export));
                startActivity(exportIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTxtTitle.setText(entry.getTitle());
        mTxtBody.setText(entry.getBody());
    }

    // return filled entry template for export
    private String exportEntry(){
        return getString(R.string.entry, entry.getTitle(),
                        DateFormat.getDateInstance().format(entry.getDate()), entry.getBody());
    }
}
