package com.example.laptop.panda_tv.ui.pandaculture.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.guide.activity.GlideImageLoader;
import com.example.laptop.panda_tv.ui.BaseFragment;
import com.example.laptop.panda_tv.ui.pandaculture.adapter.MyPandaCultureAdapter;
import com.example.laptop.panda_tv.ui.pandaculture.bean.MyPandaCultureBean;
import com.example.laptop.panda_tv.ui.pandaculture.mvp.presenter.PandaCulturePresenter;
import com.example.laptop.panda_tv.ui.pandaculture.mvp.view.IPandaCultureView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PandaCultureFragment extends BaseFragment implements IPandaCultureView {
    private Banner mBanner;
    private ListView mLvPandaCulture;

    @Override
    protected int getLayout() {
        return R.layout.fragment_panda_culture;
    }

    @Override
    protected void initView(View view) {
        View headerview = LayoutInflater.from(getActivity()).inflate(R.layout.pandaculture_header, null);
        mBanner = (Banner) headerview.findViewById(R.id.banner_pandaculture);

        mLvPandaCulture = (ListView) view.findViewById(R.id.lv_pandaCulture);
        mLvPandaCulture.addHeaderView(headerview);
    }


    @Override
    protected void initData() {
        PandaCulturePresenter presenter = new PandaCulturePresenter(this);
        presenter.setBridge();
    }

    @Override
    public void updatePandaCultureData(MyPandaCultureBean myPandaCultureBean) {
        List<MyPandaCultureBean.BigImgBean> mBigImgList = myPandaCultureBean.getBigImg();
        List<MyPandaCultureBean.ListBean> mLvPandaCultureList = myPandaCultureBean.getList();

        List<String> mBannerList = new ArrayList<>();
        for (int i = 0; i < mBigImgList.size(); i++) {
            String image = mBigImgList.get(i).getImage();
            mBannerList.add(image);
        }
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(mBannerList);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.start();

        MyPandaCultureAdapter adapter = new MyPandaCultureAdapter(mLvPandaCultureList,getActivity());
        mLvPandaCulture.setAdapter(adapter);



    }
}
