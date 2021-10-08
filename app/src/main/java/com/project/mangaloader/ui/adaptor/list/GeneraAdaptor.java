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
import com.project.mangaloader.data.model.Genera;
import com.project.mangaloader.ui.view.Activities.ListMamgaActivity;

import java.util.List;

public class GeneraAdaptor extends RecyclerView.Adapter<GeneraAdaptor.ViewHolder> {


    private Context context;
    private List<Genera> generaList;

    public GeneraAdaptor(Context context, List<Genera> generaList) {
        this.context = context;
        this.generaList = generaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.catagory_item, parent, false);
        return new GeneraAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genera genera = generaList.get(position);
        holder.textView.setText(genera.getItem());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ListMamgaActivity.class);
                intent.putExtra("url",genera.getUrl());
                intent.putExtra("name",genera.getItem());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return generaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.category_item);
        }
    }
}
