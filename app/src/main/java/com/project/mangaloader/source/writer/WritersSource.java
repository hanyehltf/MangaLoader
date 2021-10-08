package com.project.mangaloader.source.writer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.Writer;
import com.project.mangaloader.ui.adaptor.list.WritersAdaptor;
import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.project.mangaloader.data.database.CreateDB.URL_M;

public class WritersSource extends AsyncTask<Void,Integer,String> {


    private RecyclerView recyclerView;
    private CircleProgressBar circleProgressBar;
    private Context context;
    ArrayList<Writer> writers;
    public WritersSource (RecyclerView recyclerView, CircleProgressBar circleProgressBar, Context context){
        this.recyclerView = recyclerView;
        this.circleProgressBar = circleProgressBar;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        circleProgressBar.setVisibility(View.GONE);
        WritersAdaptor writersAdaptor=new WritersAdaptor(context,writers);
        recyclerView.setAdapter(writersAdaptor);
        circleProgressBar.setVisibility(View.GONE);
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

    @Override
    protected String doInBackground(Void... voids) {
        writers = new ArrayList<>();

        try {

            Document document = Jsoup.connect(URL_M).get();
            Elements element = document.select("li.menu-item-11972");
            int size =  element.select("li").size();
            for (int i = 1; i < size; i++) {
                Writer writer= new Writer();

                writer.setName(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .text());
                writer.setUrl(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .attr("href"));
                writers.add(writer);

                Log.d("item", writer.getName() + writer.getUrl());
            }


        } catch (IOException e) {
            e.printStackTrace();
            this.cancel(true);
        }





        return null;
    }
}
