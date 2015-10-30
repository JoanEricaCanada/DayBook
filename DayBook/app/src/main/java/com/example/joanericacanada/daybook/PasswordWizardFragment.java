package com.example.joanericacanada.daybook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joanericacanada.daybook.Activities.EntryListActivity;
import com.example.joanericacanada.daybook.Model.PasswordManager;

/**
 * Created by joanericacanada on 10/30/15.
 */
public class PasswordWizardFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.password_wizard_layout, parent, false);

        final EditText edtNewPassword = (EditText) view.findViewById(R.id.edtNewPassword);
        final EditText edtConfirmPassword = (EditText) view.findViewById(R.id.edtNewPassword);

        Button btnCreate = (Button)view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNewPassword.getText() == edtConfirmPassword.getText()) {
                    PasswordManager.get(getActivity()).setPassword(edtNewPassword.getText().toString());
                    Toast.makeText(getContext(), "Password Created!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), EntryListActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else
                    Toast.makeText(getContext(), "Password Mismatch. Please try again", Toast.LENGTH_SHORT).show();            }
        });
        return view;
    }
}
