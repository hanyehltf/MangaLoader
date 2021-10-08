package com.project.mangaloader.ui.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.R;
import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.source.manga.MangaInfoSource;
import com.project.mangaloader.ui.adaptor.list.LibraryRecyclerViewAdapter;


public class MangaInformationFragment extends Fragment {


    private Context context;
    private Manga manga;
    private RecyclerView recyclerView;
    private ImageView addToLib;
    private CircleProgressBar loader;

    public MangaInformationFragment(Context context, Manga manga) {
        this.context = context;

        this.manga = manga;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manga_information, container, false);
        addToLib = view.findViewById(R.id.addToLib_btn);
        loader = view.findViewById(R.id.framInfoprogressBar);
        CreateDB createDB = new CreateDB(context);
        addToLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        createDB.setLibrary(manga);
                        LibraryRecyclerViewAdapter libraryRecyclerViewAdapter = new LibraryRecyclerViewAdapter(context);
                        libraryRecyclerViewAdapter.insertData(createDB.getLibrary());
                        Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.addtolibrarymassage);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.getWindow().setGravity(Gravity.TOP);
                        dialog.show();
                        Handler handler = new Handler();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {

                                dialog.dismiss();

                            }

                        };
                        handler.postDelayed(runnable, 2000);

                    }
                };
                r.run();


            }
        });
        getMangainfo(view);
        return view;
    }


    public void getMangainfo(View view) {

        recyclerView = view.findViewById(R.id.manga_info_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MangaInfoSource mangaInfoSource = new MangaInfoSource(manga, context, recyclerView, loader);
        mangaInfoSource.execute();


    }


}