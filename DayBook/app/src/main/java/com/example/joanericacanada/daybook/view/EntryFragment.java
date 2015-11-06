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
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public class EntryFragment extends Fragment {
    //TAGS
    public static final String ENTRY_ID = "id";

    //VARIABLES
    private Entry mEntry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID)getActivity().getIntent().getSerializableExtra(ENTRY_ID);
        mEntry = EntryKeeper.get(getActivity()).getEntry(id);

        if(mEntry == null)
            mEntry = new Entry();
        mEntry.setDate(new Date());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_entry_fragment, parent, false);

        String currentDate = DateFormat.getDateTimeInstance().format(mEntry.getDate());
        TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtDate.setText(currentDate);

        EditText edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        edtTitle.setText(mEntry.getTitle());
        edtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Pattern.matches("^ +$", s.toString()))
                    s = "";
                mEntry.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });

        EditText edtBody = (EditText) view.findViewById(R.id.edtEntry);
        edtBody.setText(mEntry.getBody());
        edtBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEntry.setBody(s.toString());
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
        validateEntry();
        EntryKeeper.get(getActivity()).saveEntries();
    }

    //checks of empty fields
    private void validateEntry(){
        if (mEntry.getTitle() == null || mEntry.getTitle().equals("")) {
            if (mEntry.getBody() == null || mEntry.getBody().equals(""))
                EntryKeeper.get(getContext()).getEntries().remove(mEntry);
            else {
                String title = "(No Title)";
                int time = 1;
                for (Entry e : EntryKeeper.get(getContext()).getEntries()) {
                    if (e != mEntry && e.getTitle().equals(title + time))
                        time++;
                }
                title += time;
                mEntry.setTitle(title);
            }
        }
    }
}
