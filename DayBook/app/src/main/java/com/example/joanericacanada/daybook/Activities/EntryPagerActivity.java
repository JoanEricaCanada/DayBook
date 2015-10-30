package com.example.joanericacanada.daybook.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.joanericacanada.daybook.Controller.EntryKeeper;
import com.example.joanericacanada.daybook.Model.EntryModel;
import com.example.joanericacanada.daybook.R;
import com.example.joanericacanada.daybook.View.SelectedEntryFragment;

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
                UUID id = journal.get(position).getId();
                return SelectedEntryFragment.newInstance(id);
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
