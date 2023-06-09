package com.project.mangaloader.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.data.database.queries.CategoryQueries;
import com.project.mangaloader.data.database.queries.MangasQueries;
import com.project.mangaloader.data.model.Category;
import com.project.mangaloader.data.model.Genera;
import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.data.model.PublishYear;
import com.project.mangaloader.data.model.Style;
import com.project.mangaloader.data.model.Writer;
import com.project.mangaloader.source.category.CategorySource;


import java.util.ArrayList;
import java.util.List;

import static com.project.mangaloader.data.database.table.CategoryTable.CREATE_CATEGORY_TABLE;
import static com.project.mangaloader.data.database.table.MangaTable.CREATE_MANGAS_T;
import static com.project.mangaloader.data.database.table.MangaTable.MANGAS_T;
import static com.project.mangaloader.data.database.table.MangaTable.M_ID;

public class CreateDB extends SQLiteOpenHelper {


    public static String DATABASE_NAME = "mangareader";
    public static String URL_M = "http://myloader1.site/";
    private List<Category> categories;
    private List<Genera> generaList;
    private List<Style> styles;
    private List<Writer> writers;
    private List<PublishYear> publishYears;
    private List<Manga> mangaList;
    @Nullable
    private Context context;

    public CreateDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_MANGAS_T);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void setCategory(RecyclerView recyclerView, CircleProgressBar circleProgressBar) {
        if (getCategory().size() == 0) {
            CategorySource categorySource = new CategorySource(this,recyclerView,context,circleProgressBar);
            categorySource.execute();
        }


    }


    public List<Category> getCategory() {
        categories = new ArrayList<>();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                categories = CategoryQueries.getCategories(CreateDB.this);
            }
        };
        r.run();


        return categories;
    }










    public void setLibrary(Manga manga) {

        MangasQueries mangasQueries = new MangasQueries(this, context, manga);
        mangasQueries.execute();

    }

    public List<Manga> getLibrary() {
        mangaList = new ArrayList<>();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                mangaList = MangasQueries.getManga(CreateDB.this);
            }
        };
        runnable.run();
        return mangaList;
    }


    public void deleteFromLib(Manga manga) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(MANGAS_T, M_ID + " = " + manga.getID(), null);


    }
}
