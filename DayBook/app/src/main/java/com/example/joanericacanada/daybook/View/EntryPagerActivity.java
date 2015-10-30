package com.example.joanericacanada.daybook.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.joanericacanada.daybook.EntryKeeper;
import com.example.joanericacanada.daybook.Model.EntryModel;
import com.example.joanericacanada.daybook.R;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by joanericacanada on 10/29/15.
 */
public class EntryPagerActivity extends FragmentActivity {
    //VARIABLES
    private ViewPager entryPager;
    private ArrayList<EntryModel> journal;
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        
        entryPager = new ViewPager(this);
        entryPager.setId(R.id.viewPager);
        setContentView(entryPager);

        journal = EntryKeeper.get(this).getEntries();

        FragmentManager fragmentManager = getSupportFragmentManager();
        entryPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                EntryModel entry = journal.get(position);
                return EntryFragment.newInstance(entry.getId());
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
                EntryModel entry = journal.get(pos);
                if(entry.getTitle() != null)
                    setTitle(entry.getTitle());
            }
        });

            UUID id = (UUID)getIntent().getSerializableExtra(EntryFragment.ENTRY_ID);
        for(int i = 0; i < journal.size(); i++){
            if(journal.get(i).getId().equals(id)){
                entryPager.setCurrentItem(i);
                break;
            }
        }
    }
}
