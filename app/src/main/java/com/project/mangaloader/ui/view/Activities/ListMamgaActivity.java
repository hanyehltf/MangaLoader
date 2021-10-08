package com.project.mangaloader.ui.view.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.project.mangaloader.R;
import com.project.mangaloader.ui.view.fragments.ListFragment;

public class ListMamgaActivity extends AppCompatActivity {
private ImageView back;
private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mamga);
        back=findViewById(R.id.backtoprev);
        title=findViewById(R.id.titleofPage);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        String name=intent.getStringExtra("name");
        title.setText(name);

        ListFragment listFragment=new ListFragment(this,url);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.list_fram, listFragment, "list Fragment");
        transaction.commit();


    }
}