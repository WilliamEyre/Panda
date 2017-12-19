package com.example.laptop.panda_tv.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.home.activity.PlayVideoActivity;
import com.example.laptop.panda_tv.ui.home.bean.MyHomeBean;

import java.util.List;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MySplendidContentAdapter extends RecyclerView.Adapter<MySplendidContentAdapter.ViewHolder>{
    private List<MyHomeBean.DataBean.AreaBean.ListscrollBean> listscroll;
    private Context context;

    public MySplendidContentAdapter(List<MyHomeBean.DataBean.AreaBean.ListscrollBean> listscroll, Context context) {
        this.listscroll = listscroll;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_splendid_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTvSplendidContent.setText(listscroll.get(position).getTitle());
        Glide.with(context).load(listscroll.get(position).getImage()).into(holder.mImgSplendidContent);
        holder.mLineSplendidContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PlayVideoActivity.class);
                intent.putExtra("pid", listscroll.get(position).getPid());
                intent.putExtra("title", listscroll.get(position).getTitle());
                intent.putExtra("tag", 2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listscroll.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImgSplendidContent;
        public TextView mTvSplendidContent;
        public LinearLayout mLineSplendidContent;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgSplendidContent = (ImageView) itemView.findViewById(R.id.img_splendidContent);
            mTvSplendidContent = (TextView) itemView.findViewById(R.id.tv_splendidContent);
            mLineSplendidContent = (LinearLayout) itemView.findViewById(R.id.line_splendidContent);

        }
    }
}
