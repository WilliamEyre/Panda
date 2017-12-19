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
import com.example.laptop.panda_tv.ui.home.bean.MyHomeCctvBean;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyHomeCctvAdapter extends RecyclerView.Adapter<MyHomeCctvAdapter.ViewHolder>{
    private List<MyHomeCctvBean.ListBean> myHomeCctvBeanList;
    private Context context;

    public MyHomeCctvAdapter(List<MyHomeCctvBean.ListBean> myHomeCctvBeanList, Context context) {
        this.myHomeCctvBeanList = myHomeCctvBeanList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_home_cctv,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(myHomeCctvBeanList.get(position).getImage()).into(holder.mImgCcTv);
        holder.mTvCctv.setText(myHomeCctvBeanList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return myHomeCctvBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImgCcTv;
        public TextView mTvCctv;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgCcTv = (ImageView) itemView.findViewById(R.id.img_cctv);
            mTvCctv = (TextView) itemView.findViewById(R.id.tv_cctv);

        }
    }
}
