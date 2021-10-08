package com.project.mangaloader.ui.adaptor.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mangaloader.R;
import com.project.mangaloader.data.model.Category;
import com.project.mangaloader.ui.view.Activities.ListMamgaActivity;
import java.util.List;

public class ParentAdaptor extends RecyclerView.Adapter<ParentAdaptor.ViewHolder> {
    private Context context;
    private List<Category> categoryList;

    public ParentAdaptor(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ParentAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_list, parent, false);
        return new ParentAdaptor.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdaptor.ViewHolder holder, int position) {
        Category c = categoryList.get(position);
        holder.title.setText(c.getItem());


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(
                holder.childe_rec
                        .getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);


        holder.childe_rec.setLayoutManager(layoutManager);
        ListRecyclerViewAdaptor adaptor = new ListRecyclerViewAdaptor(context, c.getMangaList());
        holder.childe_rec.setAdapter(adaptor);
        holder.gotoPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ListMamgaActivity.class);
                intent.putExtra("url",c.getUrl());
                intent.putExtra("name",c.getItem());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView childe_rec;
        private TextView title;
        private Button gotoPage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            childe_rec = itemView.findViewById(R.id.child_rec);
            title = itemView.findViewById(R.id.category_text);
            gotoPage = itemView.findViewById(R.id.gotopage);

        }
    }
}
