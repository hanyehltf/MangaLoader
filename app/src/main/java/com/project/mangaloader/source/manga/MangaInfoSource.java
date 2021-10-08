package com.project.mangaloader.source.manga;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.ui.adaptor.setInfo.MangaInfoItemAdaptor;
import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class MangaInfoSource extends AsyncTask<Void, Integer, String> {

    private Context context;
    private RecyclerView recyclerView;
    private CircleProgressBar loader;

    private Manga manga;

    private List mangaInfo=new ArrayList();


    public MangaInfoSource(Manga manga, Context context, RecyclerView recyclerView, CircleProgressBar loader) {
        this.manga = manga;




        this.context = context;

        this.recyclerView = recyclerView;
        this.loader = loader;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loader.setVisibility(View.VISIBLE);


    }

    @Override
    protected void onPostExecute(java.lang.String s) {
        super.onPostExecute(s);
        MangaInfoItemAdaptor mangaInfoItemAdaptor = new MangaInfoItemAdaptor(context,mangaInfo);
        recyclerView.setAdapter(mangaInfoItemAdaptor);
        loader.setVisibility(View.GONE);



    }

    @Override
    protected void onProgressUpdate(java.lang.Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(java.lang.String s) {
        super.onCancelled(s);
        NetworkErrorConnection networkErrorConnection=new NetworkErrorConnection(context);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(java.lang.Void... voids) {

        getInfo();




        return null;
    }


    public void generaCategoryStyle(){}

    public void getInfo() {
        String url = manga.getDest_Url();


        try {

            Document document = Jsoup.connect(url).get();
            Elements element = document.select("div.chapinfo").select("ul").select("li").select("span");

            manga.setPer_Name(element.select("span").eq(0).text());
            manga.setEn_Name(element.select("span").eq(1).text());
            manga.setName(element.select("span").eq(2).text());
            manga.setLast_Episode(element.select("span").eq(4).text());
            manga.setWriter(element.select("span").eq(6).text());
            manga.setDesigner(element.select("span").eq(7).text());
            manga.setCountry(element.select("span").eq(8).text());
            manga.setPublish_Date(element.select("span").eq(11).text());

            Log.d("item", manga.getImageUrl() + manga.getEn_Name());


        } catch (IOException e) {
            e.printStackTrace();
this.cancel(true);
        }
        mangaInfo.add(0, manga.getName());
        mangaInfo.add(1, manga.getEn_Name());
        mangaInfo.add(2, manga.getPer_Name());
        mangaInfo.add(3, manga.getCountry());
        mangaInfo.add(4, manga.getLast_Episode());
        mangaInfo.add(5, manga.getDesigner());
        mangaInfo.add(6, manga.getWriter());
        mangaInfo.add(7, manga.getPublish_Date());




    }




}
