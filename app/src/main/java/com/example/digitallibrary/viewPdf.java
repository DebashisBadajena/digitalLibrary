package com.example.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.net.URLEncoder;

public class viewPdf extends AppCompatActivity {

    WebView pdfview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        pdfview=(WebView)findViewById(R.id.pdfwebview);

        String url=getIntent().getStringExtra("url");
        String name=getIntent().getStringExtra("name");
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle(name);
        progressDialog.setMessage("BOOK is loading......");

        pdfview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

           progressDialog.show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                progressDialog.dismiss();
            }
        });


        String encode="";
        try{

            encode= URLEncoder.encode(url,"UTF-8");
        }
        catch (Exception exception)
        {}

       pdfview.loadUrl("https://docs.google.com/gview?embedded=true&url="+encode);
        WebSettings webSettings=pdfview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        pdfview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        pdfview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        pdfview.getSettings().setAppCacheEnabled(true);

    }
}