package com.project.mangaloader.download;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.downloader.OnProgressListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.project.mangaloader.R;
import com.project.mangaloader.data.model.Chapter;
import com.project.mangaloader.ui.view.Activities.WebViewActivity;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.CallbackHandler;

public class DownloadAdaptor extends RecyclerView.Adapter<DownloadAdaptor.ViewHolder> {
    private Context context;
    private List<Chapter> chapterList;
    android.app.DownloadManager downloadManager;
    String message;
    UnZipFile unZipFile;

    public DownloadAdaptor(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
        this.downloadManager = (android.app.DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        unZipFile = new UnZipFile(context, chapterList, this);
        unZipFile.execute();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.download_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Chapter chapter = chapterList.get(position);

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if(chapter.getReadOnlineUrl()!=null && !chapter.getReadOnlineUrl().equalsIgnoreCase("")){
                    holder.readOnline.setVisibility(View.VISIBLE);


                }
            }
        };runnable.run();

        holder.readOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, WebViewActivity.class);
                intent.putExtra("url",chapter.getReadOnlineUrl());
                context.startActivity(intent);
            }
        });
        holder.file_name.setText(chapter.getTitle());
        holder.downloaded_size.setText(" دانلود شده :" + chapter.getFile_size());
        holder.downloaded_statuse.setText(message);
        if (chapter.getUrl() != "") {

            if (chapter.getStatus().equals("COMPLATE")) {


                holder.pause_resum.setText("باز کردن");
                holder.downloaded_statuse.setText("تکمیل");
                holder.downloadProgress.setVisibility(View.GONE);


            } else if (chapter.getIs_pause() && chapter.getStatus().equalsIgnoreCase("PAUSE")) {
                holder.pause_resum.setText("ادامه");

            } else if (chapter.getStatus().equalsIgnoreCase("DOWNLOADING")) {
                holder.pause_resum.setText("توقف");
            }


            holder.downloadProgress.setProgress(chapter.getProgress());


            holder.pause_resum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (chapter.getStatus().equalsIgnoreCase("START")
                            ) {
                                DownloadTask downloadTask = new DownloadTask(context,
                                        DownloadAdaptor.this, chapter);
                                chapter.setDownloadId(downloadTask.startDownload());
                            }


                            if (chapter.getIs_pause() && chapter.getStatus().equalsIgnoreCase("PAUSE")) {

                                PRDownloader.resume(chapter.getDownloadId());

                            }
                            if (!chapter.getIs_pause() && chapter.getStatus().equalsIgnoreCase("DOWNLOADING"))


                                PRDownloader.pause(chapter.getDownloadId());


                            if (chapter.getStatus().equals("COMPLATE")) {


                               unZipFile.openFolder();


                            }
                        }


                    };
                    runnable.run();


                }
            });


        } else {

            holder.pause_resum.setText("خطا");
            holder.downloaded_statuse.setText("لینکی برای دانلود وجود ندارد");
            holder.downloadProgress.setVisibility(View.GONE);
        }


    }


    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView downloaded_size;
        TextView downloaded_statuse;
        Button pause_resum;
        Button readOnline;
        ProgressBar downloadProgress;
        TextView file_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

Runnable runnable=new Runnable() {
    @Override
    public void run() {
        downloaded_size = itemView.findViewById(R.id.downloaded_size_txt);
        downloaded_statuse = itemView.findViewById(R.id.status_txt);
        pause_resum = itemView.findViewById(R.id.pause_resume);
        readOnline=itemView.findViewById(R.id.readOnline);
        downloadProgress = itemView.findViewById(R.id.download_progress);
        file_name = itemView.findViewById(R.id.file_name);
    }
};runnable.run();

        }
    }

    public void changeItem(Chapter chapter) {

Runnable runnable=new Runnable() {
    @Override
    public void run() {
        int i = 0;
        for (Chapter c : chapterList) {
            if (c == chapter) {

                notifyItemChanged(i);


            }
            i++;

        }
    }
};runnable.run();

    }

    public boolean changeItemWithStatus(Chapter chapter, String msg) {

        final boolean[] comp = {false};
        final int[] i = {0};
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                for (Chapter chapter1 : chapterList) {
                    if (chapter == chapter1) {
                        int finalI = i[0];

                        chapterList.get(finalI).setStatus(chapter.getStatus());
                        message = msg;

                        notifyItemChanged(finalI);

                    }

                    comp[0] = true;


                    i[0]++;

                }
            }
        };runnable.run();

        return comp[0];
    }


}
