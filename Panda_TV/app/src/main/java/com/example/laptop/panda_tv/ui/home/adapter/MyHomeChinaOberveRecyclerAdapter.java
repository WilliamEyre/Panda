package com.example.laptop.panda_tv.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.home.activity.PlayVideoActivity;
import com.example.laptop.panda_tv.ui.home.bean.MyHomePandaObserveBean;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyHomeChinaOberveRecyclerAdapter extends RecyclerView.Adapter<MyHomeChinaOberveRecyclerAdapter.ViewHolder> {
    private List<MyHomePandaObserveBean.ListBean> mPandaObserveList;
    private Context context;

    public MyHomeChinaOberveRecyclerAdapter(List<MyHomePandaObserveBean.ListBean> mPandaObserveList, Context context) {
        this.mPandaObserveList = mPandaObserveList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_home_shadow_china,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTvTitlehomeShadowChina.setText(mPandaObserveList.get(position).getTitle());
        holder.mTvDayTimehomeShadowChina.setText(mPandaObserveList.get(position).getDaytime());
        holder.mRbhomeShadowChina.setText(mPandaObserveList.get(position).getVideoLength());
        Glide.with(context).load(mPandaObserveList.get(position).getImage()).into(holder.mImghomeShadowChina);
        holder.mLinehomeShadowChina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayVideoActivity.class);
                intent.putExtra("pid", mPandaObserveList.get(position).getPid());
                intent.putExtra("title", mPandaObserveList.get(position).getTitle());
                intent.putExtra("tag", 3);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPandaObserveList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImghomeShadowChina;
        public TextView mTvTitlehomeShadowChina;
        public TextView mTvDayTimehomeShadowChina;
        public LinearLayout mLinehomeShadowChina;
        public RadioButton mRbhomeShadowChina;

        public ViewHolder(View itemView) {
            super(itemView);

            mImghomeShadowChina = (ImageView) itemView.findViewById(R.id.img_homeShadowChina);
            mTvTitlehomeShadowChina = (TextView) itemView.findViewById(R.id.tv_title_homeShadowChina);
            mTvDayTimehomeShadowChina = (TextView) itemView.findViewById(R.id.tv_daytime_homeShadowChina);
            mRbhomeShadowChina = (RadioButton) itemView.findViewById(R.id.rb_homeShadowChina);
            mLinehomeShadowChina = (LinearLayout) itemView.findViewById(R.id.line_homeShadowChina);

        }
    }

}
