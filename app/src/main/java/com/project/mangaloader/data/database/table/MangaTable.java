package com.project.mangaloader.data.database.table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import javax.security.auth.callback.CallbackHandler;


public class MangaTable {


    public static String M_NAME = "name";
    public static String GENERA = "genera";
    public static String M_WRITER = "writer";
    public static String M_COVER = "cover";
    public static String M_ID="m_id";
    public static String M_PER_NAME = "per_name";
    public static String M_EN_NAME = "en_name";
    public static String M_DESIGNER = "designer";
    public static String M_COUNTRY = "country";
    public static String M_DATE = "date";
    public static String M_LAST_EPISODE = "last_episode";
    public static String MANGAS_T = "mangas";
    public static String DEST_URL = "dest_url";


    public static String CREATE_MANGAS_T = "CREATE TABLE IF NOT EXISTS  " + MANGAS_T + " (" +
            M_ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+
            M_NAME + " TEXT, " +
            M_WRITER + " TEXT ," +
            M_COVER + " TEXT  , " +
            M_PER_NAME + "  TEXT ," +
            M_EN_NAME + "  TEXT ," +
            M_DESIGNER + "  TEXT ," +
            M_COUNTRY + "  TEXT ," +
            M_DATE + "  TEXT ," +
            M_LAST_EPISODE + "  TEXT ," +
            GENERA + " TEXT ," +
            DEST_URL + ");";


}
