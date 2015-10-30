package com.example.joanericacanada.daybook.View;

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

import com.example.joanericacanada.daybook.Activities.EntryActivity;
import com.example.joanericacanada.daybook.Controller.EntryKeeper;
import com.example.joanericacanada.daybook.Model.EntryModel;
import com.example.joanericacanada.daybook.R;

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
    private TextView txtTitle, txtBody, txtDate;
    private EntryModel entry;

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
        UUID id = (UUID)getActivity().getIntent().getSerializableExtra(ENTRY_ID);
        entry = EntryKeeper.get(getActivity()).getEntry(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_entry_layout, parent, false);

        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtTitle.setText(entry.getTitle());

        String currentdate = DateFormat.getDateTimeInstance().format(entry.getDate());
        txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtDate.setText(currentdate);

        txtBody = (TextView) view.findViewById(R.id.txtBody);
        txtBody.setText(entry.getBody());

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
                Intent intent = new Intent(getActivity(), EntryActivity.class);
                intent.putExtra(EntryFragment.ENTRY_ID, entry.getId());
                startActivityForResult(intent, 0);
                //startActivity(intent);
                return true;
            case R.id.delete_entry:
                ArrayList<EntryModel> journal = EntryKeeper.get(getActivity()).getEntries();
                journal.remove(entry);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        txtTitle.setText(entry.getTitle());
        txtBody.setText(entry.getBody());
    }
}
