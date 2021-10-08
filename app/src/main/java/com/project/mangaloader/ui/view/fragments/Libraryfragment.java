package com.project.mangaloader.ui.view.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.project.mangaloader.R;
import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.task.LibraryTask;


/**
 * A fragment representing a list of Items.
 */
public class Libraryfragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private Context context;
    private CreateDB createDB;
    private View view;

    public Libraryfragment(Context context) {


        this.context = context;
        createDB = new CreateDB(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.libraryfragment_list, container, false);


        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_lib);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
                flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
                flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
                flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
                recyclerView.setLayoutManager(flexboxLayoutManager);

                if (createDB.getLibrary().size() != 0) {
                    LibraryTask libraryTask = new LibraryTask(createDB, recyclerView, context);
                    libraryTask.execute();

                } else {

                    LottieAnimationView lottieAnimationView = view.findViewById(R.id.empty_lib);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    lottieAnimationView.playAnimation();
                }

            }
        };
        r.run();


        return view;
    }
}