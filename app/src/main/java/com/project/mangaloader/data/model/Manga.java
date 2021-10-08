package com.project.mangaloader.data.model;

import java.util.List;

public class Manga {


    private String imageUrl;
    private String En_Name;
    private String Category_Name;
    private String Last_Episode;
    private String Genera;
    private String Publish_Date;
    private String Per_Name;
    private String Name;
    private String Writer;
    private String Designer;
    private String Country;
    private String Dest_Url;

private int ID;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEn_Name() {
        return En_Name;
    }

    public void setEn_Name(String en_Name) {
        this.En_Name = en_Name;
    }

    public String getLast_Episode() {
        return Last_Episode;
    }

    public void setLast_Episode(String last_Episode) {
        Last_Episode = last_Episode;
    }

    public String getGenera() {
        return Genera;
    }

    public void setGenera(String genera) {
        Genera = genera;
    }

    public String getPublish_Date() {
        return Publish_Date;
    }

    public void setPublish_Date(String publish_Date) {
        Publish_Date = publish_Date;
    }

    public String getPer_Name() {
        return Per_Name;
    }

    public void setPer_Name(String per_Name) {
        Per_Name = per_Name;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getDesigner() {
        return Designer;
    }

    public void setDesigner(String designer) {
        Designer = designer;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDest_Url() {
        return Dest_Url;
    }

    public void setDest_Url(String dest_Url) {
        Dest_Url = dest_Url;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String category_Name) {
        Category_Name = category_Name;
    }
}
