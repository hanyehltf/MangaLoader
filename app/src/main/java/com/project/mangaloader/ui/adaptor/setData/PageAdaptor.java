package com.project.mangaloader.ui.adaptor.setData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.project.mangaloader.R;
import com.project.mangaloader.data.model.Page;
import com.project.mangaloader.ui.view.fragments.ListFragment;

import java.util.List;

public class PageAdaptor extends RecyclerView.Adapter<PageAdaptor.ViewHolder> {
    private Context context;
    private List<Page> pageList;
    private ImageButton next;
    private ImageButton prev;

    public PageAdaptor(Context context, List<Page> pageList, ImageButton next, ImageButton prev) {
        this.context = context;
        this.pageList = pageList;
        this.next = next;
        this.prev = prev;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pageitem, parent, false);
        return new PageAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Page page = pageList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;
                ListFragment listFragment = new ListFragment(context, page.getUrl());
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.list_fram, listFragment, "Test Fragment").addToBackStack(null).commit();
            }
        });
        if(!page.getNumber().equals("")) {
            holder.button.setText(page.getNumber());
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;
                Page nextPage = pageList.get(pageList.size() - 1);

                if (!nextPage.getUrl().isEmpty() && nextPage != null) {


                    ListFragment listFragment = new ListFragment(context, nextPage.getUrl());
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.list_fram, listFragment, "Test Fragment").addToBackStack(null).commit();


                }

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) context;
                Page prevPage = pageList.get(0);
                if (!prevPage.getUrl().isEmpty() && prevPage.getUrl() != null) {


                    ListFragment listFragment = new ListFragment(context, prevPage.getUrl());
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.list_fram, listFragment, "Test Fragment").addToBackStack(null).commit();


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pageList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.page_number_id);
        }
    }
}
