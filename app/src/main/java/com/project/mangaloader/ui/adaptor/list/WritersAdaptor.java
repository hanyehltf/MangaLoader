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
import com.project.mangaloader.data.model.Writer;
import com.project.mangaloader.ui.view.Activities.ListMamgaActivity;

import java.util.List;

public class WritersAdaptor extends RecyclerView.Adapter<WritersAdaptor.ViewHolder> {
    private Context context;
    private List<Writer> writerList;

    public WritersAdaptor(Context context, List<Writer> writerList) {
        this.context = context;
        this.writerList = writerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.catagory_item, parent, false);
        return new WritersAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Writer writer = writerList.get(position);

        holder.textView.setText(writer.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ListMamgaActivity.class);
                intent.putExtra("url",writer.getUrl());
                intent.putExtra("name",writer.getName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return writerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.category_item);
        }
    }
}
