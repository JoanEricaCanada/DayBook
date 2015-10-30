package com.example.joanericacanada.daybook.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.joanericacanada.daybook.Controller.EntryKeeper;
import com.example.joanericacanada.daybook.Model.EntryModel;
import com.example.joanericacanada.daybook.R;

import java.text.DateFormat;
import java.util.UUID;

public class EntryFragment extends Fragment {
    //TAGS
    public static final String ENTRY_ID = "id";

    //WIDGETS
    private TextView txtDate;
    private EditText edtTitle, edtBody;

    //VARIABLES
    private EntryModel entry;

    /*public static EntryFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(ENTRY_ID, id);

        EntryFragment fragment = new EntryFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID)getActivity().getIntent().getSerializableExtra(ENTRY_ID);
        entry = EntryKeeper.get(getActivity()).getEntry(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_entry_fragment, parent, false);

        if(entry == null)
            entry = new EntryModel();

        String currentdate = DateFormat.getDateTimeInstance().format(entry.getDate());
        txtDate = (TextView) view.findViewById(R.id.txtDate);
        txtDate.setText(currentdate);

        edtTitle = (EditText) view.findViewById(R.id.edtTitle);
        edtTitle.setText(entry.getTitle());
        edtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                entry.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtBody = (EditText) view.findViewById(R.id.edtEntry);
        edtBody.setText(entry.getBody());
        edtBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                entry.setBody(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        EntryKeeper.get(getActivity()).saveEntries();
    }
}
