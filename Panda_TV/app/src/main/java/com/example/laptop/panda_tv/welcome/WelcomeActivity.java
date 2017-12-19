package com.example.laptop.panda_tv.welcome;

import android.content.Intent;
import android.os.Handler;

import com.example.laptop.panda_tv.BaseActivity;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.guide.activity.GuideActivity;

/**
 * Created by Laptop on 2017/12/19.
 */
public class WelcomeActivity extends BaseActivity {

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(WelcomeActivity.this,GuideActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        handler.postDelayed(runnable,2000);
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initListener() {

    }
}
