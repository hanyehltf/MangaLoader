package com.project.mangaloader.data.model;

import java.util.List;

public class Episode {




    private List<Chapter>chapters;
private String name;
    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
