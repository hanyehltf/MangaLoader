package com.project.mangaloader.download;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.project.mangaloader.data.model.Chapter;


import static android.content.ContentValues.TAG;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadTask {


    private Context context;

    private DownloadAdaptor downloadAdaptor;
    private Chapter chapter;
    final String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public DownloadTask(Context context, DownloadAdaptor downloadAdaptor, Chapter chapter) {

        this.context = context;
        this.downloadAdaptor = downloadAdaptor;

        this.chapter = chapter;

        if (!isStoragePermissionGranted()) {
            try {
                Toast.makeText(context, "برنامه به حافظه دسترسی ندارد", Toast.LENGTH_LONG).show();
            }
            catch (WindowManager.BadTokenException e){
                Log.v(TAG, "Permission is granted");

            }


        }
    }


    private String bytesIntoHumanReadable(long bytes) {

        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;

        if ((bytes >= 0) && (bytes < kilobyte)) {
            return bytes + " B";

        } else if ((bytes >= kilobyte) && (bytes < megabyte)) {
            return (bytes / kilobyte) + " KB";

        } else if ((bytes >= megabyte) && (bytes < gigabyte)) {
            return (bytes / megabyte) + " MB";

        } else if ((bytes >= gigabyte) && (bytes < terabyte)) {
            return (bytes / gigabyte) + " GB";

        } else if (bytes >= terabyte) {
            return (bytes / terabyte) + " TB";

        } else {
            return bytes + " Bytes";
        }
    }


    public int startDownload() {
        final int[] id = {0};
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                id[0] = PRDownloader.download(chapter.getUrl(), chapter.getFile_path(), chapter.getFile_Name()).build()
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {

                                chapter.setProgress((int) ((progress.currentBytes * 100) / progress.totalBytes));
                                chapter.setFile_size(bytesIntoHumanReadable(progress.currentBytes));
                                downloadAdaptor.changeItem(chapter);
                            }
                        }).setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                chapter.setIs_pause(true);
                                chapter.setStatus("PAUSE");
                                downloadAdaptor.changeItemWithStatus(chapter, "متوقف");
                                downloadAdaptor.changeItem(chapter);
                            }
                        }).setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                chapter.setIs_pause(false);
                                chapter.setStatus("DOWNLOADING");
                                downloadAdaptor.changeItemWithStatus(chapter, "درحال دانلود");
                                downloadAdaptor.changeItem(chapter);
                            }
                        }).setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {


                            }
                        }).start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                chapter.setStatus("COMPLATE");
                                chapter.setIs_pause(true);
                                downloadAdaptor.changeItemWithStatus(chapter, "تکمیل شد");
                            }

                            @Override
                            public void onError(Error error) {


                                Log.i("eror", error.getServerErrorMessage());
                                chapter.setIs_pause(true);
                                chapter.setStatus("PAUSE");
                                downloadAdaptor.changeItemWithStatus(chapter, "خطا");
                                downloadAdaptor.changeItem(chapter);
                            }
                        });



            }
        };new Thread(runnable).start();
        return id[0];
    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");

                ActivityCompat.requestPermissions((Activity) context, permissions,101 );
                return false;
            }




        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }


    }

}
