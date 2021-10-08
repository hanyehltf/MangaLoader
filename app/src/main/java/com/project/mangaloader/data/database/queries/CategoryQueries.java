package com.project.mangaloader.data.database.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.Category;

import java.util.ArrayList;
import java.util.List;

import static com.project.mangaloader.data.database.table.CategoryTable.CATEGORY_T;
import static com.project.mangaloader.data.database.table.CategoryTable.C_NAME;
import static com.project.mangaloader.data.database.table.CategoryTable.C_URL;

public class CategoryQueries {




    public static void setOnDatabase(CreateDB createDB, List<Category> categories) {
        ContentValues contentValues = new ContentValues();


        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            contentValues.put(C_URL, category.getUrl());
            contentValues.put(C_NAME, category.getItem());

            SQLiteDatabase sqLiteDatabase = createDB.getWritableDatabase();
            sqLiteDatabase.insert(CATEGORY_T, null, contentValues);
        }


    }


    public static List<Category> getCategories(CreateDB createDB) {


        List<Category> categories = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = createDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM  " + CATEGORY_T, null);

        if (cursor.getCount() > 0 && cursor.moveToNext()) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Category category = new Category();
                category.setItem(cursor.getString(0));
                category.setUrl(cursor.getString(1));

                categories.add(category);


                cursor.moveToNext();

            }

        }
        cursor.close();
        sqLiteDatabase.close();

        return categories;
    }


}