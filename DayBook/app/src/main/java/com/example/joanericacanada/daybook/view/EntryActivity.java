package com.example.joanericacanada.daybook.view;

import android.support.v4.app.Fragment;

/**
 * Created by joanericacanada on 10/30/15.
 */
public class EntryActivity extends FragmentLoader{
    protected Fragment createFragment(){
        return new EntryFragment();
    }
}
