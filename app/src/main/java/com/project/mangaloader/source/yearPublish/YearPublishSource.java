package com.project.mangaloader.source.yearPublish;

import android.os.AsyncTask;
import android.util.Log;

import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.PublishYear;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.project.mangaloader.data.database.CreateDB.URL_M;

public class YearPublishSource extends AsyncTask<Void,Integer,String> {

    private CreateDB createDB;

    public YearPublishSource(CreateDB createDB){
        this.createDB = createDB;
    }


    @Override
    protected String doInBackground(Void... voids) {
        List<PublishYear> publishYears = new ArrayList<>();
        try {

            Document document = Jsoup.connect(URL_M).get();
            Elements element = document.select("li.menu-item-11968");
            int size = element.select("li").size();
            for (int i = 1; i < size; i++) {
                PublishYear publishYear = new PublishYear();

                publishYear.setItem(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .text());
                publishYear.setUrl(element
                        .select("li")
                        .select("a")
                        .eq(i)
                        .attr("href"));
                publishYears.add(publishYear);

                Log.d("item", publishYear.getItem() + publishYear.getUrl());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }





        return null;
    }
}
