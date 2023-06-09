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

public class MangaInfoItemAdaptor extends RecyclerView.Adapter<MangaInfoItemAdaptor.ViewHolder> {


    private Context context;
    private List<String> mangaInfo;

    public MangaInfoItemAdaptor(Context context, List<String> mangaInfo) {
        this.context = context;
        this.mangaInfo = mangaInfo;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manga_info_item, parent, false);
        return new MangaInfoItemAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String itemtxt = mangaInfo.get(position);
        holder.item.setText(itemtxt);
    }

    @Override
    public int getItemCount() {
        return mangaInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.manga_info_item_txt);
        }
    }


}
