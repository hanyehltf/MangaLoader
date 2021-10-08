package com.project.mangaloader.ui.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.project.mangaloader.R;
import com.project.mangaloader.ui.adaptor.list.CategoryAdaptor;
import com.project.mangaloader.ui.adaptor.setData.SetCategoryData;


public class Categoryfragment extends Fragment {

    private Context context;
    private RecyclerView category_rec;

    public Categoryfragment(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categoryfragment_list, container, false);
        category_rec = view.findViewById(R.id.category_rec);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setAlignItems(AlignItems.CENTER);
        flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        CategoryAdaptor categoryAdaptor = new CategoryAdaptor(context, SetCategoryData.SetCategory());
        category_rec.setLayoutManager(flexboxLayoutManager);
        category_rec.setAdapter(categoryAdaptor);
        return view;
    }


}