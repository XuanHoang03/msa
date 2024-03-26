package com.example.myapplication.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapplication.Helper.Movie;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng movie
        String createTableMovie = "CREATE TABLE movie (id INTEGER PRIMARY KEY AUTOINCREMENT, url TEXT)";
        db.execSQL(createTableMovie);
    }

    public void insertMovie(SQLiteDatabase db){
        String insertMovie = "INSERT INTO movie (url) VALUES ('https://www.youtube.com/watch?v=aPun7zuKr2A')";
        db.execSQL(insertMovie);
    }

    public Movie findMovieById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("movie", null, "id = ?", new String[] { String.valueOf(id) }, null, null, null);
        Movie movie = null;
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex("url"));
            movie = new Movie(url);
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return movie;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS movie");
        // Tạo lại bảng
        onCreate(db);
    }
}

