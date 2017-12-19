package com.example.laptop.panda_tv.ui.home.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.BaseActivity;
import com.example.laptop.panda_tv.ui.MyTitleLayout;

/**
 * Created by Laptop on 2017/12/19.
 */
public class HomeInteractionDetailsActivity extends BaseActivity {
    private WebView mHomeInteractionDetails;
    private MyTitleLayout myTitleLayout;


    @Override
    protected int getLayout() {
        return R.layout.home_activity_interactive_details;
    }

    @Override
    protected void initView() {
        mHomeInteractionDetails = (WebView) findViewById(R.id.web_homeInteractionDetails);
        myTitleLayout = (MyTitleLayout) findViewById(R.id.myTitleLayout);
    }

    @Override
    protected void initData() {

        myTitleLayout.initViewsVisible(true,false,true,false,true,false);

        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        String content = intent.getStringExtra("content");
        myTitleLayout.setPandaTv(content);
        myTitleLayout.setRightIcon(R.drawable.play_fullsrcee_share);

        WebSettings settings = mHomeInteractionDetails.getSettings();
        settings.setJavaScriptEnabled(true);
        mHomeInteractionDetails.loadUrl(url);
//        mHomeInteractionDetails.setWebViewClient(new WebViewClient(){
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                mHomeInteractionDetails.loadUrl(url);
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
        myTitleLayout.setOnPersonalBackImgListener(new MyTitleLayout.PersonalBackImgListener() {
            @Override
            public void onPersonalBackImgClick(View v) {
                finish();
            }
        });
    }
}
