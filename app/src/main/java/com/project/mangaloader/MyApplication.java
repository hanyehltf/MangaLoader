package com.project.mangaloader;

import android.app.Application;

import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

// Enabling database for resume support even after the application is killed:
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);






    }
}
