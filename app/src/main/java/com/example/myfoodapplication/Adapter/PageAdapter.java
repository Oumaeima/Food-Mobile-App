package com.example.myfoodapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myfoodapplication.Activity.login_tab_fragment;
import com.example.myfoodapplication.Activity.singup_tab_fragment;

public class PageAdapter extends FragmentStatePagerAdapter {

    int countTab;

    public PageAdapter(@NonNull FragmentManager fm, int countTab) {
        super(fm);
        this.countTab = countTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                login_tab_fragment fragment1 = new login_tab_fragment();
                return fragment1;
            case 1:
                singup_tab_fragment fragment2 = new singup_tab_fragment();
                return fragment2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return countTab;
    }
}
