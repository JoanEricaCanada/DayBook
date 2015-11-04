package com.example.joanericacanada.daybook.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.joanericacanada.daybook.R;
import com.example.joanericacanada.daybook.controller.EntryKeeper;
import com.example.joanericacanada.daybook.model.Entry;

import java.text.DateFormat;
import java.util.UUID;

public class EntryFragment extends Fragment {
    //TAGS
    public static final String ENTRY_ID = "id";

    //VARIABLES
    private Entry entry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID)getActivity().getIntent().getSerializableExtra(ENTRY_ID);
        entry = EntryKeeper.get(getActivity()).getEntry(id);

        if(entry == null)
            entry = new Entry();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_entry_fragment, parent, false);

        String currentDate = DateFormat.getDateTimeInstance().format(entry.getDate());
        TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtDate.setText(currentDate);

        EditText edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        edtTitle.setText(entry.getTitle());
        edtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                entry.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });

        EditText edtBody = (EditText) view.findViewById(R.id.edtEntry);
        edtBody.setText(entry.getBody());
        edtBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                entry.setBody(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });

        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        if(!(entry.getBody().equals("") && entry.getTitle().equals("")))
            EntryKeeper.get(getActivity()).saveEntries();
    }
}
