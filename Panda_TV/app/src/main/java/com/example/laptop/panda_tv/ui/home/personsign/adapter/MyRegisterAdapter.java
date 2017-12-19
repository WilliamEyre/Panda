package com.example.laptop.panda_tv.ui.home.personsign.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyRegisterAdapter extends FragmentPagerAdapter{
    private List<String> inittab;
    private List<Fragment> initfragment;

    public MyRegisterAdapter(FragmentManager fm, List<String> inittab, List<Fragment> initfragment) {
        super(fm);
        this.inittab = inittab;
        this.initfragment = initfragment;
    }

    @Override
    public Fragment getItem(int position) {
        return initfragment.get(position);
    }

    @Override
    public int getCount() {
        return initfragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return inittab.get(position);
    }
}
