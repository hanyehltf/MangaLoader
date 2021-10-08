package com.project.mangaloader.ui.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.R;
import com.project.mangaloader.task.GetMangaTask;

/**
 * A fragment representing a list of Items.
 */
public class ListFragment extends Fragment {


    private Context context;
    private String url;
    private ImageButton prev;
    private ImageButton next;
    private CircleProgressBar progressBar;
    private RecyclerView pageRec;
    private RecyclerView recyclerView;

    public ListFragment(Context context, String url) {
        this.context = context;
        this.url = url;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fram, container, false);

        pageRec = view.findViewById(R.id.page_rec);
        pageRec.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        prev = view.findViewById(R.id.prev_page);
        next = view.findViewById(R.id.next_page);
        recyclerView = view.findViewById(R.id.list_rec);
        progressBar = view.findViewById(R.id.listprogressBar);
        recyclerView.setLayoutManager(flexboxLayoutManager);

        GetMangaTask getMangaTask = new GetMangaTask(context, recyclerView, url, progressBar, pageRec, next, prev);
        getMangaTask.execute();


        return view;
    }
}