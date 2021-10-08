package com.project.mangaloader.ui.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.R;
import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.source.manga.MangaSource;
import com.project.mangaloader.ui.adaptor.list.ParentAdaptor;
import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;


public class MangaListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CircleProgressBar loader;
    private Context context;
    private CreateDB createDB;

    public MangaListFragment(Context context) {
        this.context = context;


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manga_list, container, false);
        createDB = new CreateDB(context);
        setUpView(view);

        if (createDB.getCategory().size() == 0) {
            createDB.setCategory(recyclerView,loader);


        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setAdaptor();
            }
        };
        runnable.run();


        return view;
    }

    private void setUpView(View view) {

        recyclerView = view.findViewById(R.id.recyclerView);
        loader = view.findViewById(R.id.mangaListprogressBar);


    }


    private void setAdaptor() {
        FlexboxLayoutManager flexboxLayoutManager=new FlexboxLayoutManager(context);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);

recyclerView.setLayoutManager(flexboxLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (createDB.getCategory().size() != 0) {
            MangaSource mangaSource = new MangaSource(context, recyclerView, createDB.getCategory(), loader);
            mangaSource.execute();

        }


    }


}
