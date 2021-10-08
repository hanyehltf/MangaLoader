package com.project.mangaloader.ui.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.R;
import com.project.mangaloader.source.chapters.ChaptersSource;


public class CoversFragment extends Fragment {

    RecyclerView recyclerView;
    CircleProgressBar circleProgressBar;
    private String url;

    public CoversFragment(String url) {
        this.url = url;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covers_item, container, false);

        // Set the adapter
        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list_download);
        circleProgressBar = view.findViewById(R.id.coversprogressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ChaptersSource chaptersSource = new ChaptersSource(context, recyclerView, circleProgressBar, url);
        chaptersSource.execute();

        return view;
    }


}


