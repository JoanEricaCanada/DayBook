package com.example.joanericacanada.daybook.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.joanericacanada.daybook.controller.EntryKeeper;
import com.example.joanericacanada.daybook.model.Entry;
import com.example.joanericacanada.daybook.R;
import com.example.joanericacanada.daybook.view.SelectedEntryFragment;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by joanericacanada on 10/29/15.
 */
public class EntryPagerActivity extends FragmentActivity {
    private ArrayList<Entry> journal;

    /*@Override
    public int getItemPosition(Object object){
        return 0;
    }*/
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ViewPager entryPager = new ViewPager(this);
        entryPager.setId(R.id.viewPager);
        setContentView(entryPager);

        journal = EntryKeeper.get(this).getEntries();

        FragmentManager fragmentManager = getSupportFragmentManager();
        entryPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Entry entry = journal.get(position);
                return SelectedEntryFragment.newInstance(entry.getId());
            }

            @Override
            public int getCount() {
                return journal.size();
            }
        });
        entryPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {

            }

            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {

            }

            public void onPageSelected(int pos) {
                Entry entry = journal.get(pos);
                if (entry.getTitle() != null)
                    setTitle(entry.getTitle());
            }
        });

        UUID id = (UUID)getIntent().getSerializableExtra(SelectedEntryFragment.ENTRY_ID);
        Log.e("UUID", id.toString());

        for(int i = 0; i < journal.size(); i++){
            if(journal.get(i).getId().equals(id)){
                entryPager.setCurrentItem(i);
                break;
            }
        }
    }
}
