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
public class PasswordWizardFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.password_wizard_layout, parent, false);

        final EditText edtNewPassword = (EditText) view.findViewById(R.id.edtNewPassword);
        final EditText edtConfirmPassword = (EditText) view.findViewById(R.id.edtConfirmPassword);

        Button btnCreate = (Button)view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = edtNewPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();

                PasswordManager pm = PasswordManager.get(getContext());
                Boolean isMatched = pm.validatePassword(newPassword, confirmPassword);

                if (isMatched) {
                    pm.setPassword(newPassword);
                    Toast.makeText(getContext(), R.string.create_password_success, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), EntryListActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }else
                    Toast.makeText(getContext(), R.string.new_password_mismatched, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
