package com.example.joanericacanada.daybook.view;

import android.support.v4.app.Fragment;

/**
 * Created by joanericacanada on 10/29/15.
 */
public class EntryListActivity extends FragmentLoader {
    protected Fragment createFragment(){
        return new EntryListFragment();
    }
}
