package com.project.mangaloader.data.model;

import java.util.List;

public class Book {
    private List<Chapter>chapters;
    private String Name;

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
