package com.example.laptop.panda_tv.ui.home.activity;

import android.content.Intent;

import com.example.laptop.panda_tv.BaseActivity;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.home.bean.LivePlayBean;
import com.google.gson.Gson;

import java.io.IOException;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PlayVideoActivity extends BaseActivity{
    private JCVideoPlayerStandard mPlayerVideo;


    @Override
    protected int getLayout() {
        return R.layout.activity_play_video;
    }

    @Override
    protected void initView() {
        mPlayerVideo = (JCVideoPlayerStandard) findViewById(R.id.playerVideo);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        String title = intent.getStringExtra("title");
        int tag = intent.getIntExtra("tag", 0);
        String url = null;


        switch (tag) {

            case 1:

                url = "http://115.182.9.189/api/getVideoInfoForCBox.do?pid=" + pid;
                break;
            case 2:
                url = "http://vdn.apps.cntv.cn/api/getVideoInfoForCBox.do?pid=" + pid;
                break;
            case 3:
                url = "http://vdn.apps.cntv.cn/api/getVideoInfoForCBox.do?pid=" + pid;
                break;
        }

        OkHttpClient ohc = new OkHttpClient();
        Request build = new Request.Builder().url(url).build();
        ohc.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                LivePlayBean livePlayBean = gson.fromJson(string, LivePlayBean.class);
                final String title1 = livePlayBean.getTitle();
                final String url1 = livePlayBean.getVideo().getChapters().get(0).getUrl();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPlayerVideo.setUp(url1, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
                        //直接进入全屏
                        mPlayerVideo.startFullscreen(PlayVideoActivity.this, JCVideoPlayerStandard.class, url1, "");
                        //模拟用户点击开始按钮，NORMAL状态下点击开始播放视频，播放中点击暂停视频
                        mPlayerVideo.startButton.performClick();

                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initListener() {

    }
}
