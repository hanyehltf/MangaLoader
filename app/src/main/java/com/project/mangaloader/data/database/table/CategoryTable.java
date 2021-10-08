package com.project.mangaloader.data.database.table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.net.PortUnreachableException;

public class CategoryTable {
    public static String CATEGORY_T = "category";

    public static String C_URL="C_URL";
    public static  String C_NAME="C_NAME";



    public static String CREATE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS  " + CATEGORY_T + " (" +
            C_NAME + " TEXT, " +
            C_URL + " TEXT );";






}
