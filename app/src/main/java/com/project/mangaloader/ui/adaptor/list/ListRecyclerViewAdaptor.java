package com.project.mangaloader.ui.adaptor.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.mangaloader.R;
import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.ui.view.Activities.MangaInfoActivity;


import java.util.List;

public class ListRecyclerViewAdaptor extends RecyclerView.Adapter<ListRecyclerViewAdaptor.ViewHolder> {
    private Context context;
    private List<Manga> mangas;

    public ListRecyclerViewAdaptor(Context context,List<Manga> mangas) {
        this.context = context;
        this.mangas = mangas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mangaitem, parent, false);
        return new ListRecyclerViewAdaptor.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Manga manga = mangas.get(position);

        if (manga.getImageUrl().isEmpty()) {
            holder.imageView.setImageResource(R.drawable.shape);
        } else {
            Glide.with(context).load(manga.getImageUrl()).into(holder.imageView);

        }
        String name = manga.getName().replace("دانلود", "");
        holder.title.setText(name);
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


    @Override
    public int getItemCount() {
        return mangas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.homeList_cover);
            title = itemView.findViewById(R.id.homeList_item);

        }
    }
}
