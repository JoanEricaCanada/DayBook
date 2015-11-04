package com.example.joanericacanada.daybook.view;

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
                Boolean isCurrentPassword = PasswordManager.get(getContext()).validatePassword(
                        edtCurrentPassword.getText().toString());
                if (isCurrentPassword){
                    Boolean isMatched = PasswordManager.get(getContext()).validatePassword(
                            edtNewPassword.getText().toString(), edtConfirmPassword.getText().toString());
                    if(isMatched) {
                        PasswordManager.get(getActivity()).setPassword(edtNewPassword.getText().toString());
                        Toast.makeText(getContext(), R.string.change_password_success, Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                }
            }
        });
        return view;
    }
}
