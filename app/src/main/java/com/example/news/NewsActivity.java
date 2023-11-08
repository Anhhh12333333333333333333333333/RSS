package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity {

    WebView webView;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webView = (WebView) findViewById(R.id.webViewNews);
        btnBack = (Button)findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String link = intent.getStringExtra("linkNews");

        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, MainActivity.class));
            }
        });
    }
}