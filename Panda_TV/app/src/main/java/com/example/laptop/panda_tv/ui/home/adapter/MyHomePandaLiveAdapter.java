package com.example.laptop.panda_tv.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.home.bean.MyHomeBean;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyHomePandaLiveAdapter extends RecyclerView.Adapter<MyHomePandaLiveAdapter.ViewHolder>{
    private List<MyHomeBean.DataBean.PandaliveBean.ListBean> list;
    private Context context;

    public MyHomePandaLiveAdapter(List<MyHomeBean.DataBean.PandaliveBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_home_panda_live,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.mImghomePandaLive);
        holder.mTvhomePandaLive.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImghomePandaLive;
        public TextView mTvhomePandaLive;

        public ViewHolder(View itemView) {
            super(itemView);

            mImghomePandaLive = (ImageView) itemView.findViewById(R.id.img_homePandaLive);
            mTvhomePandaLive = (TextView) itemView.findViewById(R.id.tv_homePandaLive);

        }
    }
}
