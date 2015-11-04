package com.example.joanericacanada.daybook.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joanericacanada.daybook.R;
import com.example.joanericacanada.daybook.model.PasswordManager;

/**
 * Created by joanericacanada on 10/30/15.
 */
public class PasswordLockFragment extends Fragment {
    //WIDGETS
    EditText edtPassword;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.password_layout, parent, false);

        edtPassword = (EditText)view.findViewById(R.id.edtPassword);
        Button btnUnlock = (Button)view.findViewById(R.id.btnUnlock);
        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PasswordManager.get(getContext()).validatePassword(edtPassword.getText().toString())){
                    Toast.makeText(getContext(), R.string.current_password_correct, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), EntryListActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        return view;
    }
}
