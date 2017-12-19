package com.example.laptop.panda_tv.ui.home.hudong.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.laptop.panda_tv.BaseActivity;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.MyTitleLayout;
import com.example.laptop.panda_tv.ui.home.bean.MyHomeBean;
import com.example.laptop.panda_tv.ui.home.hudong.adapter.MyInteractionAdapter;
import com.example.laptop.panda_tv.ui.home.hudong.bean.MyInteractionBean;
import com.example.laptop.panda_tv.ui.home.hudong.presenter.InteractionPresenter;
import com.example.laptop.panda_tv.ui.mvp.view.IHomeView;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class InteractionActivity extends BaseActivity implements IHomeView{

    private MyTitleLayout myTitleLayout;
    private RecyclerView mRecyclerInteraction;
    private List<MyInteractionBean.InteractiveBean> interactive;

    @Override
    protected int getLayout() {
        return R.layout.activity_interaction;
    }

    @Override
    protected void initView() {
        myTitleLayout = (MyTitleLayout) findViewById(R.id.myTitleLayout);
        mRecyclerInteraction = (RecyclerView) findViewById(R.id.recycler_interaction);

        InteractionPresenter presenter = new InteractionPresenter(this);
        presenter.setBridge();

    }

    @Override
    protected void initData() {
        myTitleLayout.initViewsVisible(true,false,true,false,false,false);
        myTitleLayout.setPandaTv("原创·互动");
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

    @Override
    public void updateUI(MyHomeBean myHomeBean) {

    }

    @Override
    public void updateInteractionData(MyInteractionBean myInteractionBean) {

        interactive = myInteractionBean.getInteractive();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        MyInteractionAdapter adapter = new MyInteractionAdapter(interactive,InteractionActivity.this);
        mRecyclerInteraction.setAdapter(adapter);
        mRecyclerInteraction.setLayoutManager(manager);


    }
}
