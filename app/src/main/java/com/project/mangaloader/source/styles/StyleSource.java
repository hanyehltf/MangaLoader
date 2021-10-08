package com.project.mangaloader.source.styles;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.Style;
import com.project.mangaloader.ui.adaptor.list.StyleAdaptor;
import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.mangaloader.data.database.CreateDB.URL_M;

public class StyleSource extends AsyncTask<Void,Integer,String> {

private List<Style>styleList;
    private Context context;
    private RecyclerView recyclerView;
    private CircleProgressBar progressBar;

    public StyleSource(Context context, RecyclerView recyclerView, CircleProgressBar progressBar)
    {
        this.context = context;
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(Void... voids) {
        styleList=new ArrayList<>();
        try {

            Document document = Jsoup.connect(URL_M).get();
            Elements element = document.select("li.menu-item-11969");
            int size = element.select("li").size();
            for (int i = 1; i < size; i++) {
                Style style=new Style();

                style.setName(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .text());
                style.setUrl(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .attr("href"));
                styleList.add(style);

                Log.d("item", style.getName() + style.getUrl());
            }


        } catch (IOException e) {
            e.printStackTrace();
            this.cancel(true);
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        StyleAdaptor styleAdaptor=new StyleAdaptor(context,styleList);
        recyclerView.setAdapter(styleAdaptor);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        NetworkErrorConnection networkErrorConnection=new NetworkErrorConnection(context);
    }
}
