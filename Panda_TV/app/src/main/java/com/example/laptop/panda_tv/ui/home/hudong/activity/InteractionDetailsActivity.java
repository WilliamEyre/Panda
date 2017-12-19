package com.example.laptop.panda_tv.ui.home.hudong.activity;

import android.content.Intent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.laptop.panda_tv.BaseActivity;
import com.example.laptop.panda_tv.R;

/**
 * Created by Laptop on 2017/12/19.
 */
public class InteractionDetailsActivity extends BaseActivity{
    private WebView mInteractionDetails;


    @Override
    protected int getLayout() {
        return R.layout.activity_interaction_details;
    }

    @Override
    protected void initView() {
        mInteractionDetails = (WebView) findViewById(R.id.web_interactionDetails);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");

        WebSettings settings = mInteractionDetails.getSettings();
        settings.setJavaScriptEnabled(true);
        mInteractionDetails.loadUrl(url);
        mInteractionDetails.setWebViewClient(new WebViewClient(){

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                mInteractionDetails.loadUrl(url);
//
////                return super.shouldOverrideUrlLoading(view, request);
//            }
        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initListener() {

    }
}
