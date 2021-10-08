package com.project.mangaloader.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Chapter extends RealmObject {

    private String Manga_name;
    private int DownloadId;
private String ReadOnlineUrl;
    String title;
    String file_path;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    int progress = 0;
    String status = "START";
    String File_Name;

    public String getFile_Name() {
        return File_Name;
    }

    public void setFile_Name(String file_Name) {
        File_Name = file_Name;
    }

    String file_size = "0";
    Boolean is_pause=false;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public Boolean getIs_pause() {
        return is_pause;
    }

    public void setIs_pause(Boolean is_pause) {
        this.is_pause = is_pause;
    }

    public String getManga_name() {
        return Manga_name;
    }

    public void setManga_name(String manga_name) {
        Manga_name = manga_name;
    }


    public int getDownloadId() {
        return DownloadId;
    }

    public void setDownloadId(int downloadId) {
        DownloadId = downloadId;
    }

    public String getReadOnlineUrl() {
        return ReadOnlineUrl;
    }

    public void setReadOnlineUrl(String readOnlineUrl) {
        ReadOnlineUrl = readOnlineUrl;
    }
}



