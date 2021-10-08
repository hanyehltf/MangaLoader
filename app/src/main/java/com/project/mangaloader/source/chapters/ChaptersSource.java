package com.project.mangaloader.source.chapters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.data.model.Chapter;
import com.project.mangaloader.download.DownloadAdaptor;
import com.project.mangaloader.ui.dialogs.NetworkErrorConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChaptersSource extends AsyncTask<Void, Integer, String> {

    private Context context;
    private RecyclerView recyclerView;
    private CircleProgressBar circleProgressBar;
    private String url;
    private String name;
    private List<Chapter> chapterList = new ArrayList<>();

    public ChaptersSource(Context context, RecyclerView recyclerView, CircleProgressBar circleProgressBar, String url) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.circleProgressBar = circleProgressBar;
        this.url = url;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        DownloadAdaptor downloadAdaptor = new DownloadAdaptor(context, chapterList);
        recyclerView.setAdapter(downloadAdaptor);
        circleProgressBar.setVisibility(View.GONE);


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        circleProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {


        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            Elements element = document.select("div.chaplist").select("div.download-series").select("div.down-row-serie").select("div.wppper-line");
            Elements e = document.select("div.chapinfo").select("ul").select("li").select("span");
            if (element.size() != 0) {
                for (int i = 0; i < element.size(); i++) {

                    Chapter chapter = new Chapter();
                    String name=e.select("span").eq(0).text();
                    name=name.substring(name.lastIndexOf(":")+1);
                    chapter.setManga_name(name);
chapter.setReadOnlineUrl(element.select("div.chapleft").select("div.b").select("a").eq(i).attr("href"));
                    chapter.setTitle(element.select("div.chapright").eq(i).text());
                    chapter.setUrl(element.select("div.chapleft").select("div.a").select("a").eq(i).attr("href"));

if(!chapter.getUrl().equalsIgnoreCase("")) {
    String extension = chapter.getUrl().substring(chapter.getUrl().lastIndexOf("."));
    String fileName = chapter.getManga_name() + " "+chapter.getTitle() + extension;
    chapter.setFile_Name(fileName);
    String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .getAbsolutePath();
    chapter.setFile_path(downloadPath);
    chapterList.add(chapter);
}

                }


            }

        } catch (IOException e) {
            e.printStackTrace();
            this.cancel(true);
        }


        return null;
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        NetworkErrorConnection networkErrorConnection=new NetworkErrorConnection(context);
    }
}
