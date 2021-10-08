package com.project.mangaloader.ui.adaptor.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mangaloader.R;
import com.project.mangaloader.data.model.Style;
import com.project.mangaloader.data.model.Writer;
import com.project.mangaloader.ui.view.Activities.ListMamgaActivity;

import java.util.List;

public class StyleAdaptor extends RecyclerView.Adapter<StyleAdaptor.ViewHolder> {
    private Context context;
    private List<Style> styleList;

    public StyleAdaptor(Context context, List<Style> styleList) {
        this.context = context;
        this.styleList = styleList;
    }

    @NonNull
    @Override
    public StyleAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.catagory_item, parent, false);
        return new StyleAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Style style=styleList.get(position);
        holder.textView.setText(style.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ListMamgaActivity.class);
                intent.putExtra("url",style.getUrl());
                intent.putExtra("name",style.getName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return styleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.category_item);
        }
    }
}
