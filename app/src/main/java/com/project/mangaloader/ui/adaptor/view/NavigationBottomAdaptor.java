package com.project.mangaloader.ui.adaptor.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class NavigationBottomAdaptor extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public NavigationBottomAdaptor(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {




        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
