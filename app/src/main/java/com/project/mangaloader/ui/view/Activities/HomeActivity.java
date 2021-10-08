package com.project.mangaloader.ui.view.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.project.mangaloader.Network.NetworkConnection;
import com.project.mangaloader.R;

import com.project.mangaloader.ui.adaptor.view.NavigationBottomAdaptor;
import com.project.mangaloader.ui.view.fragments.Categoryfragment;
import com.project.mangaloader.ui.view.fragments.Libraryfragment;
import com.project.mangaloader.ui.view.fragments.MangaListFragment;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {


    private CoordinatorLayout coordinatorLayout;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkConnection networkConnection = new NetworkConnection(this);
        networkConnection.execute();
        setViewofActivity();
        new Thread(new Runnable() {
            @Override
            public void run() {
                setNavigationBottom();


            }
        }).start();


    }


    private void setNavigationBottom() {


        setViewPager();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.library: {
                        viewPager.setCurrentItem(0);
                        return true;
                    }

                    case R.id.list: {
                        viewPager.setCurrentItem(1);
                        return true;
                    }
                    case R.id.category: {
                        viewPager.setCurrentItem(2);
                        return true;
                    }


                }
                return false;
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.library).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.list).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.category).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setViewPager() {
        fragmentList.add(new Libraryfragment(this));
        fragmentList.add(new MangaListFragment(this));
        fragmentList.add(new Categoryfragment(this));
        NavigationBottomAdaptor adaptor = new NavigationBottomAdaptor(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adaptor);
    }


    private void setViewofActivity() {
        coordinatorLayout = findViewById(R.id.coordinator_main);
        bottomNavigationView = findViewById(R.id.navigaton_bottom);
        viewPager = findViewById(R.id.mani_viewpager);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}

