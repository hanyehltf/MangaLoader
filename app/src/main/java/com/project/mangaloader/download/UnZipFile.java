package com.project.mangaloader.download;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.project.mangaloader.data.model.Chapter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipFile;

public class UnZipFile extends AsyncTask<String, String, String> {


    private Context context;
    private List<Chapter> chapterList;
    private DownloadAdaptor downloadAdaptor;


    public UnZipFile(Context context, List<Chapter> chapterList, DownloadAdaptor downloadAdaptor) {
        this.context = context;
        this.chapterList = chapterList;
        this.downloadAdaptor = downloadAdaptor;


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
    public void checkFileExist() {

        for (Chapter chapter : chapterList) {

            try {
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS) + "/" + chapter.getFile_Name());

                if (file.exists()) {
                  chapter.setFile_size(  bytesIntoHumanReadable(file.length()));
                    chapter.setStatus("COMPLATE");
                    downloadAdaptor.changeItem(chapter);

                }
            }catch (Exception exception){
                Log.d("error",exception.getMessage().toString());

            }



        }


    }

    public void openFolder() {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath());
                intent.setDataAndType(uri, "*/*");
                context.startActivity(intent);
            }
        };new Thread(runnable).start();


    }

    @Override
    protected String doInBackground(String... strings) {


        checkFileExist();
        return null;
    }
}
