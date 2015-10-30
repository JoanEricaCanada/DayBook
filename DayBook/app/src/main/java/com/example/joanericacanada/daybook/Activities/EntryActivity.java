package com.example.joanericacanada.daybook.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.joanericacanada.daybook.R;
import com.example.joanericacanada.daybook.View.EntryFragment;

/**
 * Created by joanericacanada on 10/30/15.
 */
public class EntryActivity extends FragmentActivity {
    protected Fragment createFragment(){
        return new EntryFragment();
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
