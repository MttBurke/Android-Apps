package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;
    public PagerAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch  (position)
        {
            case 0: return new tab_Fragment1();
            case 1: return new tab_Fragment2();
            case 2: return new tab_Fragment3();
            default: return null;
        }
    }

    @Override
    public int getCount()
    {
        return 0;
    }
}
