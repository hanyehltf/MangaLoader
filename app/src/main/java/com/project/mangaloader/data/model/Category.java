package com.project.mangaloader.data.model;

import java.util.List;

public class Category {
private String url;
private String item;
private List<Manga>mangaList;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public List<Manga> getMangaList() {
        return mangaList;
    }

    public void setMangaList(List<Manga> mangaList) {
        this.mangaList = mangaList;
    }
}
