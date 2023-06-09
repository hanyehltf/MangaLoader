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
import com.project.mangaloader.ui.view.Activities.GeneraActivity;
import com.project.mangaloader.ui.view.Activities.StyleActivity;
import com.project.mangaloader.ui.view.Activities.WritersActivity;

import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHoder> {


    private Context context;
    private List<String> category;

    public CategoryAdaptor(Context context, List<String> category) {
        this.context = context;
        this.category = category;
    }


    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new CategoryAdaptor.ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        String c = category.get(position);


        holder.textView.setText(c);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                switch (position) {
                    case 0: {

                        intent = new Intent(context, GeneraActivity.class);
                        context.startActivity(intent);

                    }break;
                    case 1: {
                        intent = new Intent(context, StyleActivity.class);
                        context.startActivity(intent);
                    }
break;
                    case 2: {
                        intent = new Intent(context, WritersActivity.class);
                        context.startActivity(intent);
                    }
break;
                    default:break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);


            textView = (TextView) itemView.findViewById(R.id.item_txt);
        }
    }
}
