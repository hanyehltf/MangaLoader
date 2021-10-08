package com.project.mangaloader.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.data.model.Page;
import com.project.mangaloader.ui.adaptor.list.MangaListPageAdaptor;
import com.project.mangaloader.ui.adaptor.setData.PageAdaptor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetMangaTask extends AsyncTask<Void, Integer, String> {


    private RecyclerView recyclerView;
    private String url;
    private CircleProgressBar progressBar;
    private RecyclerView pagerec;
    private ImageButton next;
    private ImageButton prev;
    private Context context;
    private List<Manga> mangaList = new ArrayList<>();
    private List<Page> pages = new ArrayList<>();

    public GetMangaTask(Context context, RecyclerView recyclerView, String url, CircleProgressBar progressBar, RecyclerView pagerec, ImageButton next, ImageButton prev) {
        this.context = context;
        this.recyclerView = recyclerView;

        this.url = url;
        this.progressBar = progressBar;

        this.pagerec = pagerec;
        this.next = next;
        this.prev = prev;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setShowProgressText(true);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);

    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        progressBar.setProgress(values[0]);


    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MangaListPageAdaptor mangaListPageAdaptor = new MangaListPageAdaptor(context, mangaList);
        recyclerView.setAdapter(mangaListPageAdaptor);
        PageAdaptor pageAdaptor = new PageAdaptor(context, pages, next, prev);
        pagerec.setAdapter(pageAdaptor);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    protected String doInBackground(Void... voids) {

        try {


            Document document = Jsoup.connect(url).get();
            Elements element = document.select("div.box-index");

            int size = element.size();
            for (int i = 0; i < size; i++) {
                Manga manga = new Manga();
                manga.setDest_Url(element.select("div.tt-film").select("a").eq(i).attr("href"));
                manga.setImageUrl(element.select("div.box-index").select("img").eq(i).attr("src"));
                manga.setName(element.select("div.tt-film").select("a").select("h2").eq(i).text());

                mangaList.add(manga);

                Log.d("item", manga.getImageUrl() + manga.getEn_Name());
                publishProgress((i * 100) / size);

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            Document document = Jsoup.connect(url).get();
            Elements next = document.select("div.wp-pagenavi").select("a");
            if (next.size() != 0) {
                for (int i = 0; i < next.size(); i++) {
                    Page page = new Page();
                    page.setUrl(next.select("a").eq(i).attr("href"));
                    page.setNumber(next.select("a").eq(i).text());
                    if (!page.getNumber().equalsIgnoreCase("")) {
                        pages.add(page);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}



