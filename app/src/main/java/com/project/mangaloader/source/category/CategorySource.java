package com.project.mangaloader.source.category;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.database.queries.CategoryQueries;
import com.project.mangaloader.data.model.Category;
import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.source.manga.MangaSource;
import com.project.mangaloader.ui.adaptor.list.ParentAdaptor;
import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.mangaloader.data.database.CreateDB.URL_M;

public class CategorySource extends AsyncTask<Void, Integer, String> {

    private List<Category> categories;
    private CreateDB createDB;
    private RecyclerView recyclerView;
    private Context context;
    private CircleProgressBar circleProgressBar;


    public CategorySource(CreateDB createDB, RecyclerView recyclerView, Context context, CircleProgressBar circleProgressBar) {

        this.createDB = createDB;
        this.recyclerView = recyclerView;
        this.context = context;
        this.circleProgressBar = circleProgressBar;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MangaSource mangaSource = new MangaSource(context, recyclerView,categories, circleProgressBar);
        mangaSource.execute();

    }



    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Void... voids) {
        categories = new ArrayList<>();
        try {

            Document document = Jsoup.connect(URL_M).get();
            Elements element = document.select("li.menu-item-11962");
            int size =  element.select("li").size();
            for (int i = 1; i < size; i++) {
                Category category = new Category();

                category.setItem(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .text());
                category.setUrl(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .attr("href"));
                categories.add(category);

                Log.d("item", category.getItem() + category.getUrl());
            }


        } catch (IOException e) {
            e.printStackTrace();
            NetworkErrorConnection networkErrorConnection=new NetworkErrorConnection(context);

        }


        CategoryQueries.setOnDatabase(createDB,categories);


        return null;
    }


}
