package com.project.mangaloader.source.genera;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.Genera;
import com.project.mangaloader.ui.adaptor.list.GeneraAdaptor;
import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.mangaloader.data.database.CreateDB.URL_M;

public class GeneraSource extends AsyncTask<Void, Integer, String> {


    private List<Genera> generaList;
    private Context context;
    private RecyclerView recyclerView;
    private CircleProgressBar progressBar;

    public GeneraSource(Context context, RecyclerView recyclerView, CircleProgressBar progressBar) {
        this.context = context;


        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        GeneraAdaptor generaAdaptor = new GeneraAdaptor(context, generaList);
        recyclerView.setAdapter(generaAdaptor);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        NetworkErrorConnection networkErrorConnection=new NetworkErrorConnection(context);
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            generaList = new ArrayList<>();
            Document document = Jsoup.connect(URL_M).get();
            Elements element = document.select("li.menu-item-11968");
            int size = element.select("li").size();
            for (int i = 1; i < size; i++) {
                Genera genera = new Genera();

                genera.setItem(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .text());
                genera.setUrl(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .attr("href"));
                generaList.add(genera);
                publishProgress((i * 100) / generaList.size());
                Log.d("item", genera.getItem() + genera.getUrl());
            }


        } catch (IOException e) {
            e.printStackTrace();
            this.cancel(true);

        }


        return null;
    }
}
