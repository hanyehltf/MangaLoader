package com.project.mangaloader.ui.adaptor.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.ui.view.fragments.CoversFragment;
import com.project.mangaloader.ui.view.fragments.MangaInformationFragment;


public class TabsAdaptor extends FragmentStatePagerAdapter {


    private int numOfTab;
    private Context context;
    private Manga manga;


    public TabsAdaptor(FragmentManager fm, int NumOfTab, Context context, Manga manga) {
        super(fm);


        numOfTab = NumOfTab;
        this.context = context;


        this.manga = manga;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new Fragment();


        switch (i) {
            case 0:


                fragment = new CoversFragment(manga.getDest_Url());
                break;
            case 1:

                fragment = new MangaInformationFragment(context,manga);
                break;

            default:

        }


        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTab;
    }
}
