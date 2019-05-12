package com.codepolitan.viewpager2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.codepolitan.viewpager2.model.Artikel;

public class DetailActivity extends AppCompatActivity {

    WebView webView;
    String title, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        webView = findViewById(R.id.webview);
        Artikel artikel = getIntent().getParcelableExtra("artikel");
        getSupportActionBar().setTitle(artikel.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(artikel.getLink());

    }
}
