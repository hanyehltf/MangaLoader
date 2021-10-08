package com.project.mangaloader.source.manga;

import android.content.Context;

import android.os.AsyncTask;


import androidx.recyclerview.widget.RecyclerView;


import com.project.mangaloader.ui.adaptor.setInfo.MangaHeaderAdaptor;
import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ParsHeaderInfo extends AsyncTask<Void, Integer, Void> {


    private Context context;
    private String url;
    private RecyclerView recyclerView;
    private List<String> headerInfo = new ArrayList<>();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
        NetworkErrorConnection networkErrorConnection=new NetworkErrorConnection(context);
    }



    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MangaHeaderAdaptor mangaHeaderAdaptor = new MangaHeaderAdaptor(context, headerInfo);
        recyclerView.setAdapter(mangaHeaderAdaptor);
    }

    public ParsHeaderInfo(Context context, String url, RecyclerView recyclerView) {
        this.context = context;
        this.url = url;

        this.recyclerView = recyclerView;
    }

    @Override
    protected Void doInBackground(Void... voids) {



        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("header.tt-single").select("ul");
            headerInfo.add(elements.select("ul").select("li").eq(0).text());
            headerInfo.add(elements.select("ul").select("li").eq(1).text());
            headerInfo.add(elements.select("ul").select("li").eq(2).text());
            Elements team = document.select("header.tt-single").select("a.intro-team");
            headerInfo.add(team.text());


        } catch (IOException e) {
            e.printStackTrace();
cancel(true);
this.cancel(true);
        }


        return null;
    }
}
