package com.project.mangaloader.source.manga;

import android.content.Context;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.data.model.Category;
import com.project.mangaloader.data.model.Manga;

import com.project.mangaloader.ui.adaptor.list.ParentAdaptor;
import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.mangaloader.data.database.CreateDB.URL_M;

public class MangaSource extends AsyncTask<Void, Integer, String> {


    private RecyclerView recyclerView;
    private List<Category> categoryList;
    private CircleProgressBar progressBar;
    private ParentAdaptor adaptor;
    private Context context;


    public MangaSource(Context context, RecyclerView recyclerView, List<Category> categoryList, CircleProgressBar progressBar) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.categoryList = categoryList;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setShowArrow(true);
        progressBar.setProgress(0);


    }

    @Override
    protected void onCancelled(String s) {

        super.onCancelled(s);
        NetworkErrorConnection networkErrorConnection = new NetworkErrorConnection(context);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        adaptor = new ParentAdaptor(context, categoryList);
        recyclerView.setAdapter(adaptor);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    protected String doInBackground(Void... voids) {


        try {

            int j = 0;
            for (Category c : categoryList) {

                Document document = Jsoup.connect(c.getUrl()).get();
                Elements element = document.select("div.box-index");
                List<Manga> mangaList = new ArrayList<>();
                int size = element.size();
                for (int i = 0; i < element.size(); i++) {
                    Manga manga = new Manga();
                    manga.setDest_Url(element.select("div.tt-film").select("a").eq(i).attr("href"));
                    manga.setImageUrl(element.select("div.box-index").select("img").eq(i).attr("src"));
                    manga.setName(element.select("div.tt-film").select("a").select("h2").eq(i).text());

                    mangaList.add(manga);

                    Log.d("item", manga.getImageUrl() + manga.getEn_Name());

                }
                j++;
                publishProgress((j * 100) / categoryList.size());
                c.setMangaList(mangaList);
            }

        } catch (IOException e) {
            e.printStackTrace();

            this.cancel(true);


        }


        return null;
    }


}



