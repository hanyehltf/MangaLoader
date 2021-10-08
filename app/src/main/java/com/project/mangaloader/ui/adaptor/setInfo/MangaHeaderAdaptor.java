package com.project.mangaloader.ui.adaptor.setInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mangaloader.R;

import java.util.List;

public class MangaHeaderAdaptor extends RecyclerView.Adapter<MangaHeaderAdaptor.Viewholder> {


    private Context context;
    private List<String> mangaInfo;

    public MangaHeaderAdaptor(Context context, List<String> mangaInfo) {
        this.context = context;
        this.mangaInfo = mangaInfo;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_item, parent, false);
        return new MangaHeaderAdaptor.Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String item = mangaInfo.get(position);
        holder.item.setText(item);
    }

    @Override
    public int getItemCount() {
        return mangaInfo.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView item;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.manga_header_item_txt);
        }
    }
}
