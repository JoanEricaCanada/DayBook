package com.example.joanericacanada.daybook.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.joanericacanada.daybook.R;
import com.example.joanericacanada.daybook.controller.EntryKeeper;
import com.example.joanericacanada.daybook.model.Entry;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by joanericacanada on 10/29/15.
 */
public class EntryPagerActivity extends FragmentActivity {
    private ArrayList<Entry> mJournal;
    private ViewPager entryPager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        entryPager = new ViewPager(this);
        entryPager.setId(R.id.viewPager);
        setContentView(entryPager);

        mJournal = EntryKeeper.get(this).getEntries();

        FragmentManager fragmentManager = getSupportFragmentManager();
        entryPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Entry entry = mJournal.get(position);
                return SelectedEntryFragment.newInstance(entry.getId());
            }

            @Override
            public int getCount() {
                return mJournal.size();
            }
        });
        entryPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
                //do nothing
            }

            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {
                //do nothing
            }

            public void onPageSelected(int pos) {
                Entry entry = mJournal.get(pos);
                if (entry.getTitle() != null)
                    setTitle(entry.getTitle());
            }
        });

        UUID id = (UUID)getIntent().getSerializableExtra(SelectedEntryFragment.ENTRY_ID);
        Log.e("UUID", id.toString());

        for(int i = 0; i < mJournal.size(); i++){
            if(mJournal.get(i).getId().equals(id)){
                entryPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();

    }
}
