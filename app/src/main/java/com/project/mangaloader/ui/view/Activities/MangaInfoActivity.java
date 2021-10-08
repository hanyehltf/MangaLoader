package com.project.mangaloader.ui.view.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.project.mangaloader.R;

import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.source.manga.ParsHeaderInfo;
import com.project.mangaloader.ui.adaptor.view.TabsAdaptor;


import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;


public class MangaInfoActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private Manga manga;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private ImageView back;
    final String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE};
    // permission code for action multiple permission request:
    private static final int ALL_PERMISSIONS = 101;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (ALL_PERMISSIONS) {
            case 101:
                // if grantResults is populated:
                if (grantResults.length > 0) {
                    // 1° permission check:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MangaInfoActivity.this, "دسترسی با موفقیت داده شد", Toast.LENGTH_LONG).show();
                    } else {
                    }
                    // 2° permission check. NOTE: write permission is such as READ permission?
                    if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MangaInfoActivity.this, "دسترسی با موفقیت داده شد", Toast.LENGTH_LONG).show();
                    } else {
                    }
                    if (grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MangaInfoActivity.this, "دسترسی با موفقیت داده شد", Toast.LENGTH_LONG).show();
                    } else {
                    }
                }
        }
    }

    private void requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, 101);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 101);
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(MangaInfoActivity.this, new String[]{WRITE_EXTERNAL_STORAGE}, 101);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_info);
        ActivityCompat.requestPermissions(this, permissions, ALL_PERMISSIONS);
        recyclerView = findViewById(R.id.manga_header_rec);
        requestPermission();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = findViewById(R.id.backfrommangainfo);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        getMangaInfo();
        getHeaderInfo();
        setDrawer();
    }

    private void getHeaderInfo() {


        ParsHeaderInfo parsHeaderInfo = new ParsHeaderInfo(this, manga.getDest_Url(), recyclerView);
        parsHeaderInfo.execute();


    }

    private void getMangaInfo() {
        Intent intent = getIntent();
        manga = new Manga();
        manga.setDest_Url(intent.getStringExtra("manga_Dest"));
        manga.setImageUrl(intent.getStringExtra("image_url"));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (!Environment.isExternalStorageManager()) {
                    Toast.makeText(this, "اجازه دسترسی به فایل هارا بدهید", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void setDrawer() {
        tabLayout = findViewById(R.id.manga_info_tabs);
        imageView = findViewById(R.id.manga_info_cover);
        Glide.with(this).load(manga.getImageUrl()).into(imageView);
        tabLayout.addTab(tabLayout.newTab().setText("جلد ها"));
        tabLayout.addTab(tabLayout.newTab().setText("اطلاعات"));

        ViewPager viewPager = findViewById(R.id.viewpager);

        new Thread(new Runnable() {
            @Override
            public void run() {
                viewPager.setAdapter(new TabsAdaptor(getSupportFragmentManager(), tabLayout.getTabCount(), MangaInfoActivity.this, manga));
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

            }
        }).start();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
