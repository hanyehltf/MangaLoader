package com.project.mangaloader.ui.adaptor.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.mangaloader.R;
import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.ui.dialogs.DeleteMassageDialog;
import com.project.mangaloader.ui.view.Activities.MangaInfoActivity;

import java.util.List;


public class LibraryRecyclerViewAdapter extends RecyclerView.Adapter<LibraryRecyclerViewAdapter.ViewHolder> {

    private List<Manga> mValues;
    private Context context;


    public LibraryRecyclerViewAdapter(Context context) {


        this.context = context;

    }

    public void insertData(List<Manga> mangaList) {

        this.mValues = mangaList;
        notifyDataSetChanged();
        notifyItemInserted(mValues.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_libraryfragment_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Manga manga = mValues.get(position);
                holder.mIdView.setText(manga.getPer_Name());
                Glide.with(context).load(manga.getImageUrl()).into(holder.mView);
                holder.clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete(manga, mValues);


                    }
                });


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MangaInfoActivity.class);
                        intent.putExtra("image_url", manga.getImageUrl());
                        intent.putExtra("manga_Dest", manga.getDest_Url());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                });
            }
        };
        runnable.run();


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final ImageView mView;
        public ImageView clear;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.libList_item);
            mView = (ImageView) view.findViewById(R.id.libList_cover);
            clear = (ImageView) view.findViewById(R.id.clearFromLib);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }

    public void delete(Manga manga, List<Manga> mangaList) {

        CreateDB createDB = new CreateDB(context);
        DeleteMassageDialog deleteMassageDialog = new DeleteMassageDialog(context, mangaList, createDB, manga, LibraryRecyclerViewAdapter.this);


    }


    public void deleteItem(Manga manga) {
        int i = 0;
        for (Manga m : mValues
        ) {


            if (m == manga) {
                notifyItemRemoved(i);
                notifyItemChanged(i);
            }
            i++;

        }
    }




}