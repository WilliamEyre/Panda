package com.example.laptop.panda_tv.ui.home.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.laptop.panda_tv.BaseActivity;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.MyTitleLayout;

/**
 * Created by Laptop on 2017/12/19.
 */
public class BannerActivity extends BaseActivity{
    private WebView web_banner;
    private MyTitleLayout myTitleLayout;


    @Override
    protected int getLayout() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView() {
        web_banner = (WebView) findViewById(R.id.web_banner);
        myTitleLayout = (MyTitleLayout) findViewById(R.id.myTitleLayout);
    }

    @Override
    protected void initData() {

        myTitleLayout.initViewsVisible(true, false, false, false,true,false);
        myTitleLayout.setRightIcon(R.drawable.play_fullsrcee_share);

        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");

        WebSettings settings = web_banner.getSettings();
        settings.setJavaScriptEnabled(true);
        web_banner.loadUrl(url);
//        web_banner.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                web_banner.loadUrl(url);
//
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initListener() {
        myTitleLayout.setOnPersonSignListener(new MyTitleLayout.PersonSignListener() {
            @Override
            public void onPersonSignClick(View v) {

            }
        });
        myTitleLayout.setOnPersonalBackImgListener(new MyTitleLayout.PersonalBackImgListener() {
            @Override
            public void onPersonalBackImgClick(View v) {
                finish();
            }
        });
    }
}
