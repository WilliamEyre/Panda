package com.example.laptop.panda_tv.ui.home.personsign.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.laptop.panda_tv.BaseActivity;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.MyTitleLayout;
import com.example.laptop.panda_tv.ui.home.personsign.adapter.MyRegisterAdapter;
import com.example.laptop.panda_tv.ui.home.personsign.fragment.EmailsRegisterFragment;
import com.example.laptop.panda_tv.ui.home.personsign.fragment.PhoneRegisterFragment;
import com.example.laptop.panda_tv.ui.home.personsign.viewpage.MyViewPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyRegisterActivity extends BaseActivity{
    private MyTitleLayout mTitleLayout;
    private TabLayout mTabRegister;
    private MyViewPage mVpRegister;
    private List<String> inittab;
    private List<Fragment> initfragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_register;
    }

    @Override
    protected void initView() {
        mTitleLayout = (MyTitleLayout) findViewById(R.id.myTitleLayout);
        mTabRegister = (TabLayout) findViewById(R.id.tab_myRegister);
        mVpRegister = (MyViewPage) findViewById(R.id.vp_myRegister);
    }

    @Override
    protected void initData() {

        mTitleLayout.initViewsVisible(true, false, true, false, false, false);
        mTitleLayout.setPandaTv("注册");


        inittab = new ArrayList<>();
        inittab.add("手机注册");
        inittab.add("邮箱注册");

        initfragment = new ArrayList<>();
        initfragment.add(new PhoneRegisterFragment());
        initfragment.add(new EmailsRegisterFragment());

    }

    @Override
    protected void initAdapter() {
        MyRegisterAdapter adapter = new MyRegisterAdapter(getSupportFragmentManager(), inittab, initfragment);
        mVpRegister.setAdapter(adapter);
        mVpRegister.setCurrentItem(0);
        mTabRegister.setupWithViewPager(mVpRegister);
    }

    @Override
    protected void initListener() {
        mTitleLayout.setOnPersonalBackImgListener(new MyTitleLayout.PersonalBackImgListener() {
            @Override
            public void onPersonalBackImgClick(View v) {
                finish();
            }
        });
    }
}
