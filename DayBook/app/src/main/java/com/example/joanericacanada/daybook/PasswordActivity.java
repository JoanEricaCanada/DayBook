package com.example.joanericacanada.daybook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.joanericacanada.daybook.Model.PasswordManager;

/**
 * Created by joanericacanada on 10/30/15.
 */
public class PasswordActivity extends FragmentActivity {
    protected Fragment createFragment(){
        if(PasswordManager.get(this).getPassword() == null){
            return new PasswordWizardFragment();
        }else
            return new PasswordLockFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
