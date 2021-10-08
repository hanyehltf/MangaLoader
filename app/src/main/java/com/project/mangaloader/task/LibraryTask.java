package com.project.mangaloader.task;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.Library;
import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.ui.adaptor.list.LibraryRecyclerViewAdapter;

import java.util.List;

public class LibraryTask extends AsyncTask<Void, Void, Void> {


    private CreateDB createDB;
    private RecyclerView recyclerView;
    private Context context;
    private List<Manga> mangaList;

    public LibraryTask(CreateDB createDB, RecyclerView recyclerView, Context context) {
        this.createDB = createDB;
        this.recyclerView = recyclerView;
        this.context = context;
    }


    @Override
    protected void onPostExecute(Void aVoid) {

        LibraryRecyclerViewAdapter libraryRecyclerViewAdapter = new LibraryRecyclerViewAdapter(context);
        libraryRecyclerViewAdapter.insertData(mangaList);
        recyclerView.setAdapter(libraryRecyclerViewAdapter);
        libraryRecyclerViewAdapter.notifyDataSetChanged();

        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {


        mangaList = createDB.getLibrary();
        return null;
    }
}
