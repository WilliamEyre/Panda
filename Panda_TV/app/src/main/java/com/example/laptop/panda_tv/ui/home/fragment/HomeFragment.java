package com.example.laptop.panda_tv.ui.home.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.guide.activity.GlideImageLoader;
import com.example.laptop.panda_tv.ui.BaseFragment;
import com.example.laptop.panda_tv.ui.MyApplication;
import com.example.laptop.panda_tv.ui.home.activity.BannerActivity;
import com.example.laptop.panda_tv.ui.home.activity.HomeInteractionDetailsActivity;
import com.example.laptop.panda_tv.ui.home.activity.PlayVideoActivity;
import com.example.laptop.panda_tv.ui.home.adapter.MyHomeCctvAdapter;
import com.example.laptop.panda_tv.ui.home.adapter.MyHomeChinaOberveRecyclerAdapter;
import com.example.laptop.panda_tv.ui.home.adapter.MyHomeLiveChinaAdapter;
import com.example.laptop.panda_tv.ui.home.adapter.MyHomePandaLiveAdapter;
import com.example.laptop.panda_tv.ui.home.adapter.MyHomeShadowChainaAdapter;
import com.example.laptop.panda_tv.ui.home.adapter.MyHomeWallLiveAdapter;
import com.example.laptop.panda_tv.ui.home.adapter.MySplendidContentAdapter;
import com.example.laptop.panda_tv.ui.home.adapter.MySplendidTitleAdapter;
import com.example.laptop.panda_tv.ui.home.bean.MyHomeBean;
import com.example.laptop.panda_tv.ui.home.bean.MyHomeCctvBean;
import com.example.laptop.panda_tv.ui.home.bean.MyHomePandaObserveBean;
import com.example.laptop.panda_tv.ui.home.bean.MyHomeShadowBean;
import com.example.laptop.panda_tv.ui.home.hudong.bean.MyInteractionBean;
import com.example.laptop.panda_tv.ui.mvp.presenter.HomePresenter;
import com.example.laptop.panda_tv.ui.mvp.view.IHomeView;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Laptop on 2017/12/19.
 */
public class HomeFragment extends BaseFragment implements IHomeView {

    private ListView mLvShadowChina;
    private TextView mTvShadowChina;
    private List<MyHomeBean.DataBean.BigImgBean> bigImg;
    private ArrayList<String> mArr;
    private Banner mFlyBanner;
    private HomePresenter presenter;
    private Handler handler = new Handler();
    private View headerview;
    private ListView mSplendidTitle;
    private RecyclerView mSplendidContent;
    private MyHomeBean.DataBean.AreaBean area;
    private List<MyHomeBean.DataBean.AreaBean> areaBeanList;
    private List<MyHomeBean.DataBean.AreaBean.ListscrollBean> listscroll;
    private MyHomeBean.DataBean.PandaeyeBean pandaeye;
    private TextView mTitlePandaObserve;
    private TextView mTitle2PandaObserve;
    private TextView mContentPandaObserve;
    private TextView mContent2PandaObserve;
    private TextView mTvPandaObserve;
    private TextView mTvPandaLive;
    private TextView mTvWallLive;
    private TextView mTvInteractiveTitle;
    private TextView mTvInteractiveContent;
    private TextView mTvLiveChina;
    private TextView mTvCctv;
    private ImageView mImgInteractive;
    private ImageView mImgPandaObserve;
    private RecyclerView mRecyclerPandaLive;
    private RecyclerView mRecyclerWallLive;
    private RecyclerView mRecyclerLiveChina;
    private List<MyHomeBean.DataBean.PandaeyeBean.ItemsBean> items;
    private MyHomeBean.DataBean.PandaliveBean pandalive;
    private List<MyHomeBean.DataBean.PandaliveBean.ListBean> list;
    private MyHomeBean.DataBean.WallliveBean walllive;
    private List<MyHomeBean.DataBean.WallliveBean.ListBeanX> wallliveList;
    private MyHomeBean.DataBean.ChinaliveBean chinalive;
    private List<MyHomeBean.DataBean.ChinaliveBean.ListBeanXX> chinaliveList;
    private MyHomeBean.DataBean data;
    private MyHomeBean.DataBean.InteractiveBean interactive;
    private List<MyHomeBean.DataBean.InteractiveBean.InteractiveoneBean> interactiveoneBeanList;
    private MyHomeBean.DataBean.CctvBean cctv;
    private String listurl;
    private List<MyHomeCctvBean.ListBean> myHomeCctvBeanList;
    private RecyclerView mRecyclerCCTV;
    private List<MyHomeBean.DataBean.ListBeanXXX> dataList;
    private List<MyHomeShadowBean.ListBean> newListBean;
//    private View footerview;
    private String datalistUrl;
    private RecyclerView mRecyclerPandaObserve;
    private String mPandaEyeUrl;
    private List<MyHomePandaObserveBean.ListBean> mPandaObserveList;
    private List<String> mTitle;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

        headerview = LayoutInflater.from(getActivity()).inflate(R.layout.home_header, null);
        mFlyBanner = (Banner) headerview.findViewById(R.id.flybanner_home);

        mLvShadowChina = (ListView) view.findViewById(R.id.lv_ShadowChina);
        mTvShadowChina = (TextView) headerview.findViewById(R.id.tv_ShadowChina);
        mSplendidTitle = (ListView) headerview.findViewById(R.id.lv_splendid_title);
        mSplendidContent = (RecyclerView) headerview.findViewById(R.id.recycler_splendid_content);
        mTitlePandaObserve = (TextView) headerview.findViewById(R.id.tv_title_pandaObserve);
        mTitle2PandaObserve = (TextView) headerview.findViewById(R.id.tv_title2_pandaObserve);
        mContentPandaObserve = (TextView) headerview.findViewById(R.id.tv_content_pandaObserve);
        mContent2PandaObserve = (TextView) headerview.findViewById(R.id.tv_content2_pandaObserve);
        mTvPandaObserve = (TextView) headerview.findViewById(R.id.tv_pandaObserve);
        mImgPandaObserve = (ImageView) headerview.findViewById(R.id.img_pandaObserve);
        mRecyclerPandaLive = (RecyclerView) headerview.findViewById(R.id.recycler_PandaLive);
        mTvPandaLive = (TextView) headerview.findViewById(R.id.tv_PandaLive);
        mRecyclerWallLive = (RecyclerView) headerview.findViewById(R.id.recycler_WallLive);
        mTvWallLive = (TextView) headerview.findViewById(R.id.tv_WallLive);
        mRecyclerLiveChina = (RecyclerView) headerview.findViewById(R.id.recycler_LiveChina);
        mTvLiveChina = (TextView) headerview.findViewById(R.id.tv_LiveChina);
        mTvInteractiveTitle = (TextView) headerview.findViewById(R.id.tv_Interactive_title);
        mTvInteractiveContent = (TextView) headerview.findViewById(R.id.tv_Interactive_content);
        mTvCctv = (TextView) headerview.findViewById(R.id.tv_CCTV);
        mImgInteractive = (ImageView) headerview.findViewById(R.id.img_Interactive);
        mRecyclerCCTV = (RecyclerView) headerview.findViewById(R.id.recycler_CCTV);
        mRecyclerPandaObserve = (RecyclerView) headerview.findViewById(R.id.recycler_home_PandaObserve);


        mLvShadowChina.addHeaderView(headerview);
        presenter = new HomePresenter(this);
    }


    @Override
    protected void initData() {
        presenter.setBridge();
    }


    @Override
    public void updateUI(MyHomeBean myHomeBean) {
        //获取到的总的实体类
        data = myHomeBean.getData();
        //获取到的其中的实体类
        bigImg = data.getBigImg();
        area = data.getArea();
        pandaeye = data.getPandaeye();
        pandalive = data.getPandalive();
        walllive = data.getWalllive();
        chinalive = data.getChinalive();
        interactive = data.getInteractive();
        cctv = data.getCctv();

        //获取到其中的集合
        listscroll = area.getListscroll();
        items = pandaeye.getItems();
        list = pandalive.getList();
        wallliveList = walllive.getList();
        chinaliveList = chinalive.getList();
        interactiveoneBeanList = interactive.getInteractiveone();
        listurl = cctv.getListurl();
        dataList = data.getList();
        datalistUrl = dataList.get(0).getListUrl();
        mPandaEyeUrl = pandaeye.getPandaeyelist();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //轮播图界面
                initFlyBanner();

                //精彩推荐标题界面
                initSplendidTitle();

                //精彩推荐内容界面
                initSplendidContent();

                //熊猫观察界面
                initPandaObserveData();

                //熊猫直播界面
                initPandaLiveData();

                //长城直播界面
                initWallLiveData();

                //直播中国界面
                initLiveChinaData();

                //特别策划界面
                initInteractive();

                //cctv界面
                initCctv();

                //光影中国界面
                initShadowChina();

            }


        }, 2000);

    }

    @Override
    public void updateInteractionData(MyInteractionBean myInteractionBean) {

    }


    /**
     * 光影中国界面
     */
    private void initShadowChina() {
        mTvShadowChina.setText(dataList.get(0).getTitle());


        OkHttpClient ohc = new OkHttpClient();
        Request build = new Request.Builder().url(datalistUrl).build();
        ohc.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        MyHomeShadowBean myHomeShadowBean = gson.fromJson(string, MyHomeShadowBean.class);
                        newListBean = myHomeShadowBean.getList();

                        MyHomeShadowChainaAdapter adapter1 = new MyHomeShadowChainaAdapter(newListBean, getActivity());
                        mLvShadowChina.setAdapter(adapter1);
                    }
                });
            }
        });

        mLvShadowChina.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra("pid", newListBean.get(position).getPid());
                intent.putExtra("title", newListBean.get(position).getTitle());
                intent.putExtra("tag", 3);
                startActivity(intent);
            }
        });

    }

    /***
     * cctv界面
     */
    private void initCctv() {
        mTvCctv.setText(cctv.getTitle());

        OkHttpClient ohc = new OkHttpClient();
        Request build = new Request.Builder().url(listurl).build();
        ohc.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        MyHomeCctvBean myHomeCctvBean = gson.fromJson(string, MyHomeCctvBean.class);
                        myHomeCctvBeanList = myHomeCctvBean.getList();

                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                        MyHomeCctvAdapter adapter = new MyHomeCctvAdapter(myHomeCctvBeanList, getActivity());
                        mRecyclerCCTV.setLayoutManager(manager);
                        mRecyclerCCTV.setAdapter(adapter);
                    }
                });
            }
        });

    }

    /**
     * 特别策划界面
     */
    private void initInteractive() {
        mTvInteractiveTitle.setText(interactive.getTitle());
        mTvInteractiveContent.setText(interactiveoneBeanList.get(0).getTitle());
        Glide.with(getActivity()).load(interactiveoneBeanList.get(0).getImage()).into(mImgInteractive);
        mImgInteractive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeInteractionDetailsActivity.class);
                intent.putExtra("content",interactiveoneBeanList.get(0).getTitle());
                intent.putExtra("url",interactiveoneBeanList.get(0).getUrl());
                startActivity(intent);
            }
        });


    }

    /**
     * 直播中国界面
     */
    private void initLiveChinaData() {
        mTvLiveChina.setText(chinalive.getTitle());

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        MyHomeLiveChinaAdapter myHomeLiveChinaAdapter = new MyHomeLiveChinaAdapter(chinaliveList, getActivity());
        mRecyclerLiveChina.setAdapter(myHomeLiveChinaAdapter);
        mRecyclerLiveChina.setLayoutManager(manager);
    }

    /**
     * 长城直播界面
     */
    private void initWallLiveData() {
        mTvWallLive.setText(walllive.getTitle());

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        MyHomeWallLiveAdapter myPandaLiveAdapter = new MyHomeWallLiveAdapter(wallliveList, getActivity());
        mRecyclerWallLive.setLayoutManager(manager);
        mRecyclerWallLive.setAdapter(myPandaLiveAdapter);
    }

    /**
     * 熊猫直播界面
     */
    private void initPandaLiveData() {
        mTvPandaLive.setText(pandalive.getTitle());

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        MyHomePandaLiveAdapter myPandaLiveAdapter = new MyHomePandaLiveAdapter(list, getActivity());
        mRecyclerPandaLive.setLayoutManager(manager);
        mRecyclerPandaLive.setAdapter(myPandaLiveAdapter);

    }

    /**
     * 熊猫观察界面
     */
    private void initPandaObserveData() {
        mTvPandaObserve.setText(pandaeye.getTitle());
        Glide.with(getActivity()).load(pandaeye.getPandaeyelogo()).into(mImgPandaObserve);
        mTitlePandaObserve.setText(items.get(0).getBrief());
        mContentPandaObserve.setText(items.get(0).getTitle());
        mTitle2PandaObserve.setText(items.get(1).getBrief());
        mContent2PandaObserve.setText(items.get(1).getTitle());

        /**
         * 熊猫观察网络请求
         */
        OkHttpClient ohc = new OkHttpClient();
        Request request = new Request.Builder().url(mPandaEyeUrl).build();
        ohc.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("TAG", e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                mPandaObserveList = new ArrayList<MyHomePandaObserveBean.ListBean>();
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        MyHomePandaObserveBean myHomePandaObserveBean = gson.fromJson(string, MyHomePandaObserveBean.class);
                        List<MyHomePandaObserveBean.ListBean> mPandaObserveList = myHomePandaObserveBean.getList();

                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        MyHomeChinaOberveRecyclerAdapter shadowChinaRecyclerAdapter = new MyHomeChinaOberveRecyclerAdapter(mPandaObserveList, getActivity());
                        mRecyclerPandaObserve.setAdapter(shadowChinaRecyclerAdapter);
                        mRecyclerPandaObserve.setLayoutManager(manager);

                    }
                });
            }
        });

        /**
         * 新生点击事件
         */
        mContentPandaObserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra("pid", pandaeye.getItems().get(0).getPid());
                intent.putExtra("title", pandaeye.getItems().get(0).getTitle());
                intent.putExtra("tag", 3);
                startActivity(intent);
            }
        });
        /**
         * 趣闻点击事件
         */
        mContent2PandaObserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                intent.putExtra("pid", pandaeye.getItems().get(0).getPid());
                intent.putExtra("title", pandaeye.getItems().get(0).getTitle());
                intent.putExtra("tag", 3);
                startActivity(intent);
            }
        });


    }

    /**
     * 精彩推荐内容界面
     */
    private void initSplendidContent() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        MySplendidContentAdapter mySplendidContentAdapter = new MySplendidContentAdapter(listscroll, getActivity());
        mSplendidContent.setAdapter(mySplendidContentAdapter);
        mSplendidContent.setLayoutManager(manager);
    }

    /**
     * 精彩推荐标题界面
     */
    private void initSplendidTitle() {
        areaBeanList = new ArrayList<>();
        areaBeanList.add(area);
        MySplendidTitleAdapter mySplendidTitleAdapter = new MySplendidTitleAdapter(areaBeanList, getActivity());
        mSplendidTitle.setAdapter(mySplendidTitleAdapter);
    }

    /**
     * 轮播图界面
     */
    private void initFlyBanner() {
        mArr = new ArrayList<>();
        mTitle = new ArrayList<>();
        for (int i = 0; i < bigImg.size(); i++) {
            String image = bigImg.get(i).getImage();
            String title = bigImg.get(i).getTitle();
            mArr.add(image);
            mTitle.add(title);
        }
        //设置图片加载器
        mFlyBanner.setImageLoader(new GlideImageLoader());
        mFlyBanner.setImages(mArr);
        //设置指示器位置（当banner模式中有指示器时）
        mFlyBanner.setBannerTitles(mTitle);
        mFlyBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mFlyBanner.start();


        mFlyBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), BannerActivity.class);
                    intent.putExtra("url", bigImg.get(0).getUrl());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
                    intent.putExtra("pid", bigImg.get(position).getPid());
                    intent.putExtra("title", bigImg.get(position).getTitle());
                    intent.putExtra("tag", 1);
                    startActivity(intent);
                }
            }
        });

    }
}
