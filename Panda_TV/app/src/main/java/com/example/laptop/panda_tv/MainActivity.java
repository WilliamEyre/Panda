package com.example.laptop.panda_tv;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.laptop.panda_tv.ui.MyTitleLayout;
import com.example.laptop.panda_tv.ui.home.fragment.HomeFragment;
import com.example.laptop.panda_tv.ui.home.hudong.activity.InteractionActivity;
import com.example.laptop.panda_tv.ui.home.personsign.activity.PersonSignActicity;
import com.example.laptop.panda_tv.ui.livechina.fragment.LiveChinaFragment;
import com.example.laptop.panda_tv.ui.pandaculture.fragment.PandaCultureFragment;
import com.example.laptop.panda_tv.ui.pandalive.fragment.PandaLiveFragment;
import com.example.laptop.panda_tv.ui.pandaobserve.fragment.PandaObserveFragment;

public abstract class MainActivity extends BaseActivity {
    private RadioGroup mMainRg;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private PandaLiveFragment pandaLiveFragment;
    private PandaCultureFragment pandaCultureFragment;
    private PandaObserveFragment pandaObserveFragment;
    private LiveChinaFragment liveChinaFragment;
    private FragmentManager manager;
    private MyTitleLayout myTitleLayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mMainRg = (RadioGroup) findViewById(R.id.main_rg);
        myTitleLayout = (MyTitleLayout) findViewById(R.id.myTitleLayout);

    }

    @Override
    protected void initData() {

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        homeFragment = new HomeFragment();
        pandaLiveFragment = new PandaLiveFragment();
        pandaCultureFragment = new PandaCultureFragment();
        pandaObserveFragment = new PandaObserveFragment();
        liveChinaFragment = new LiveChinaFragment();
        transaction.add(R.id.main_frame, homeFragment);
        transaction.add(R.id.main_frame, pandaLiveFragment);
        transaction.add(R.id.main_frame, pandaCultureFragment);
        transaction.add(R.id.main_frame, pandaObserveFragment);
        transaction.add(R.id.main_frame, liveChinaFragment);
        transaction.show(homeFragment);
        transaction.hide(pandaLiveFragment);
        transaction.hide(pandaCultureFragment);
        transaction.hide(pandaObserveFragment);
        transaction.hide(liveChinaFragment);

        transaction.commit();
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initListener() {
        mMainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                transaction = manager.beginTransaction();

                switch (checkedId) {
                    case R.id.rb_Home:


                        myTitleLayout.initViewsVisible(false, true, false, true, true,false);


                        transaction.show(homeFragment);
                        transaction.hide(pandaLiveFragment);
                        transaction.hide(pandaCultureFragment);
                        transaction.hide(pandaObserveFragment);
                        transaction.hide(liveChinaFragment);

                        break;
                    case R.id.rb_PandaLive:

                        myTitleLayout.initViewsVisible(false, false, true, false, true,false);
                        myTitleLayout.setPandaTv("熊猫直播");

                        transaction.show(pandaLiveFragment);
                        transaction.hide(homeFragment);
                        transaction.hide(pandaCultureFragment);
                        transaction.hide(pandaObserveFragment);
                        transaction.hide(liveChinaFragment);

                        break;
                    case R.id.rb_PandaCulture:

                        myTitleLayout.initViewsVisible(false, false, true, false, true,false);
                        myTitleLayout.setPandaTv("熊猫文化");

                        transaction.show(pandaCultureFragment);
                        transaction.hide(pandaLiveFragment);
                        transaction.hide(homeFragment);
                        transaction.hide(pandaObserveFragment);
                        transaction.hide(liveChinaFragment);

                        break;
                    case R.id.rb_PandaObserve:

                        myTitleLayout.initViewsVisible(false, false, true, false, true,false);
                        myTitleLayout.setPandaTv("熊猫观察");

                        transaction.show(pandaObserveFragment);
                        transaction.hide(pandaLiveFragment);
                        transaction.hide(pandaCultureFragment);
                        transaction.hide(homeFragment);
                        transaction.hide(liveChinaFragment);

                        break;
                    case R.id.rb_LiveChina:

                        myTitleLayout.initViewsVisible(false, false, true, false, true,false);
                        myTitleLayout.setPandaTv("直播中国");

                        transaction.show(liveChinaFragment);
                        transaction.hide(pandaLiveFragment);
                        transaction.hide(pandaCultureFragment);
                        transaction.hide(pandaObserveFragment);
                        transaction.hide(homeFragment);

                        break;
                }
                transaction.commit();
            }
        });

        myTitleLayout.setOnHudongSignListener(new MyTitleLayout.HudongSignListener() {
            @Override
            public void onHuDongSignClick(View v) {
                Intent intent = new Intent(MainActivity.this,InteractionActivity.class);
                startActivity(intent);
            }
        });

        myTitleLayout.setOnPersonSignListener(new MyTitleLayout.PersonSignListener() {
            @Override
            public void onPersonSignClick(View v) {
                Intent intent = new Intent(MainActivity.this,PersonSignActicity.class);
                startActivity(intent);
            }
        });
    }
}
