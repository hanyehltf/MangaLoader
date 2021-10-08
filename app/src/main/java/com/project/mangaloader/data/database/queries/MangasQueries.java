package com.project.mangaloader.data.database.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.Category;
import com.project.mangaloader.data.model.Manga;

import java.util.ArrayList;
import java.util.List;

import static com.project.mangaloader.data.database.table.CategoryTable.CATEGORY_T;
import static com.project.mangaloader.data.database.table.MangaTable.DEST_URL;
import static com.project.mangaloader.data.database.table.MangaTable.GENERA;
import static com.project.mangaloader.data.database.table.MangaTable.MANGAS_T;
import static com.project.mangaloader.data.database.table.MangaTable.M_COUNTRY;
import static com.project.mangaloader.data.database.table.MangaTable.M_COVER;
import static com.project.mangaloader.data.database.table.MangaTable.M_DATE;
import static com.project.mangaloader.data.database.table.MangaTable.M_DESIGNER;
import static com.project.mangaloader.data.database.table.MangaTable.M_EN_NAME;
import static com.project.mangaloader.data.database.table.MangaTable.M_ID;
import static com.project.mangaloader.data.database.table.MangaTable.M_LAST_EPISODE;
import static com.project.mangaloader.data.database.table.MangaTable.M_NAME;
import static com.project.mangaloader.data.database.table.MangaTable.M_PER_NAME;
import static com.project.mangaloader.data.database.table.MangaTable.M_WRITER;


public class MangasQueries extends AsyncTask<Void, Integer, String> {


    private CreateDB createDB;
    private Context context;
    private Manga manga;

    public MangasQueries(CreateDB createDB, Context context, Manga manga) {
        this.createDB = createDB;
        this.context = context;
        this.manga = manga;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(Void... voids) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(M_NAME, manga.getName());
        contentValues.put(GENERA, manga.getGenera());
        contentValues.put(M_WRITER, manga.getWriter());
        contentValues.put(M_COVER, manga.getImageUrl());
        contentValues.put(M_PER_NAME, manga.getPer_Name());
        contentValues.put(M_EN_NAME, manga.getEn_Name());
        contentValues.put(M_DESIGNER, manga.getDesigner());
        contentValues.put(M_COUNTRY, manga.getCountry());
        contentValues.put(M_DATE, manga.getPublish_Date());
        contentValues.put(DEST_URL, manga.getDest_Url());
        contentValues.put(M_LAST_EPISODE, manga.getLast_Episode());
        SQLiteDatabase sqLiteDatabase = createDB.getWritableDatabase();
        sqLiteDatabase.insert(MANGAS_T, null, contentValues);


        return null;
    }


    public static List<Manga> getManga(CreateDB createDB) {
        List<Manga> mangaList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = createDB.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  " + MANGAS_T, null);

        if (cursor.getCount() > 0 && cursor.moveToNext()) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Manga manga = new Manga();
                        manga.setID(cursor.getInt(0));
                        manga.setName(cursor.getString(1));
                        manga.setWriter(cursor.getString(2));
                        manga.setImageUrl(cursor.getString(3));
                        manga.setPer_Name(cursor.getString(4));
                        manga.setEn_Name(cursor.getString(5));
                        manga.setDesigner(cursor.getString(6));
                        manga.setCountry(cursor.getString(7));
                        manga.setPublish_Date(cursor.getString(8));
                        manga.setLast_Episode(cursor.getString(9));
                        manga.setGenera(cursor.getString(10));
                        manga.setDest_Url(cursor.getString(11));


                        mangaList.add(manga);
                        cursor.moveToNext();
                    }
                };
                runnable.run();


            }

        }
        cursor.close();


        sqLiteDatabase.close();
        return mangaList;
    }


}
