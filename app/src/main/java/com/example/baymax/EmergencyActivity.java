package com.example.baymax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class EmergencyActivity extends AppCompatActivity {

    private WebView webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        webView2=findViewById(R.id.webmdview2);
        WebSettings webSettings=webView2.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView2.loadUrl("http://www.criticarebd.com/");
    }
}
