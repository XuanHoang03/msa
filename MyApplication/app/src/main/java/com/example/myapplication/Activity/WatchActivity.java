package com.example.myapplication.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Helper.DatabaseHelper;
import com.example.myapplication.Helper.Movie;
import com.example.myapplication.R;

public class WatchActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.insertMovie(db);



        Movie movie = dbHelper.findMovieById(1); // Thay 1 bằng id của phim bạn muốn tìm
        if (movie != null) {
            // Phim được tìm thấy
            String url = movie.getUrl();
            WebView webView = findViewById(R.id.webView);

            Toast.makeText(this,url,Toast.LENGTH_LONG).show();
            String html = "<iframe width=\"100%\" height=\"100%\" src=\""+url+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";

            webView.loadData(html, "text/html", "utf-8");
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
        }





    }
}
