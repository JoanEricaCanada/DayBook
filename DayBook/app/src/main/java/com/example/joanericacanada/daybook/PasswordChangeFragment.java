package com.example.joanericacanada.daybook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joanericacanada.daybook.Model.PasswordManager;

/**
 * Created by joanericacanada on 10/30/15.
 */
public class PasswordChangeFragment extends Fragment {
    //WIDGETS
    private EditText edtCurrentPassword, edtNewPassword, edtConfirmPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.password_wizard_layout2, parent, false);

        edtCurrentPassword = (EditText) view.findViewById(R.id.edtPassword);
        edtNewPassword = (EditText) view.findViewById(R.id.edtNewPassword);
        edtConfirmPassword = (EditText) view.findViewById(R.id.edtConfirmPassword);

        Button btnChange = (Button) view.findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = PasswordManager.get(getActivity()).getPassword();
                if (edtCurrentPassword.getText().toString().equals(password)){
                    if(edtNewPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                        PasswordManager.get(getActivity()).setPassword(edtNewPassword.getText().toString());
                        Toast.makeText(getContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }else
                        Toast.makeText(getContext(), "New Password Mismatched! Please try again.",
                                Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getContext(), "Incorrect password! Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
