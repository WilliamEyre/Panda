package com.example.laptop.panda_tv.ui.pandaobserve.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.BaseFragment;
import com.example.laptop.panda_tv.ui.pandaobserve.adapter.MyPandaObserveAdapter;
import com.example.laptop.panda_tv.ui.pandaobserve.bean.MyPandaObserveBean;
import com.example.laptop.panda_tv.ui.pandaobserve.bean.MyPandaObserveRecyclerBean;
import com.example.laptop.panda_tv.ui.pandaobserve.mvp.presenter.PandaObservePresenter;
import com.example.laptop.panda_tv.ui.pandaobserve.mvp.view.IPandaOberveView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PandaObserveFragment extends BaseFragment implements IPandaOberveView {
    private List<MyPandaObserveBean.DataBean.BigImgBean> mPandaobserveList;
    private ListView mLvPandaObserve;
    private TextView mTvPandaObserve;
    private ImageView mImgvPandaObserve;
    private View headerview;
    private List<MyPandaObserveRecyclerBean.ListBean> mPandaObserveRecyclerList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_panda_observe;
    }

    @Override
    protected void initView(View view) {

        headerview = LayoutInflater.from(getActivity()).inflate(R.layout.pandaobserve_head, null);

        mLvPandaObserve = (ListView) view.findViewById(R.id.lv_pandaObserve);
        mTvPandaObserve = (TextView) headerview.findViewById(R.id.tv_pandaObserve);
        mImgvPandaObserve = (ImageView) headerview.findViewById(R.id.img_pandaObserve);


    }


    @Override
    protected void initData() {
        PandaObservePresenter presenter = new PandaObservePresenter(this);
        presenter.setBridge();
    }

    @Override
    public void updatePandaOberveData(MyPandaObserveBean myPandaObserveBean) {
        mPandaobserveList = myPandaObserveBean.getData().getBigImg();

        mTvPandaObserve.setText(mPandaobserveList.get(0).getTitle());
        Glide.with(getActivity()).load(mPandaobserveList.get(0).getImage()).into(mImgvPandaObserve);

        String mPandaObserveUrl = myPandaObserveBean.getData().getListurl();

        OkHttpClient ohc = new OkHttpClient();
        Request request = new Request.Builder().url(mPandaObserveUrl).build();
//        ohc.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }

//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String string = response.body().string();
//                context.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Gson gson = new Gson();
//                        MyPandaObserveRecyclerBean myPandaObserveRecyclerBean = gson.fromJson(string, MyPandaObserveRecyclerBean.class);
//                        mPandaObserveRecyclerList = myPandaObserveRecyclerBean.getList();
//
//                        MyPandaObserveAdapter adapter = new MyPandaObserveAdapter(mPandaObserveRecyclerList,getActivity());
//                        mLvPandaObserve.setAdapter(adapter);
//                        mLvPandaObserve.addHeaderView(headerview);
//                    }
//                });
//            }
//        });

//        mLvPandaObserve.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
  }
}
