package com.example.laptop.panda_tv.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.laptop.panda_tv.R;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyTitleLayout extends LinearLayout{
    private MyViewHolder mViewHolder;
    private PersonalBackImgListener personalBackImgListener;
    private HudongSignListener hudongSignListener;
    private PersonSignListener personSignListener;
    private RegisterListener registerListener;

    public MyTitleLayout(Context context) {
        this(context,null);
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        View mTitleLayout = inflater.inflate(R.layout.title_layout, null);
        this.addView(mTitleLayout, layoutParams);

        mViewHolder = new MyViewHolder(this);
        mViewHolder.mPersonalBackImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (personalBackImgListener!= null) {
                    personalBackImgListener.onPersonalBackImgClick(v);
                }
            }
        });
        mViewHolder.mHudongSign.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hudongSignListener != null) {
                    hudongSignListener.onHuDongSignClick(v);
                }
            }
        });
        mViewHolder.mPersonSign.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(personSignListener != null){
                    personSignListener.onPersonSignClick(v);
                }
            }
        });
        mViewHolder.mRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerListener != null){
                    registerListener.onRegisterClick(v);
                }
            }
        });
    }

    public MyTitleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTitleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void initViewsVisible(boolean isLeftPersonalBackImgVisile,boolean isLeftPandaSignVisible, boolean isCenterPandaTvVisile, boolean isHudongSignVisile, boolean isPersonSignVisile,boolean isRegisterVisile){
        //左侧的返回
        mViewHolder.mPersonalBackImg.setVisibility(isLeftPersonalBackImgVisile ? View.VISIBLE : View.INVISIBLE);

        //左侧的图片
        mViewHolder.mPandaSign.setVisibility(isLeftPandaSignVisible ? View.VISIBLE : View.INVISIBLE);

        //中间的标题
        mViewHolder.mPandaTv.setVisibility(isCenterPandaTvVisile ? View.VISIBLE : View.INVISIBLE);

        //右侧互动图标
        mViewHolder.mHudongSign.setVisibility(isHudongSignVisile ? View.VISIBLE : View.INVISIBLE);

        //右侧个人中心图标
        mViewHolder.mPersonSign.setVisibility(isPersonSignVisile ? View.VISIBLE : View.INVISIBLE);

        //右侧注册
        mViewHolder.mRegister.setVisibility(isRegisterVisile ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 设置中间的标题
     * @param title
     */
    public void setPandaTv(String title) {
        if (!TextUtils.isEmpty(title)) {
            mViewHolder.mPandaTv.setText(title);
        }
    }

    /**public void setAppBackground(int color)
     {
     viewAppTitle.setBackgroundColor(color);
     }*/
    public void setRightIcon(int icon){
        mViewHolder.mPersonSign.setImageResource(icon);
    }

    public void setOnPersonalBackImgListener(PersonalBackImgListener listener){
        this.personalBackImgListener = listener;
    }

    public void setOnHudongSignListener(HudongSignListener listener){
        this.hudongSignListener = listener;
    }

    public void setOnPersonSignListener(PersonSignListener listener){
        this.personSignListener = listener;
    }
    public void setOnRegisterListener(RegisterListener listener){
        this.registerListener = listener;
    }

    public static abstract interface PersonalBackImgListener{
        public abstract void onPersonalBackImgClick(View v);
    }

    public static abstract interface HudongSignListener{
        public abstract void onHuDongSignClick(View v);
    }

    public static abstract interface PersonSignListener{
        public abstract void onPersonSignClick(View v);
    }

    public static abstract interface RegisterListener{
        public abstract void onRegisterClick(View v);
    }

    class MyViewHolder{
        private ImageView mHudongSign;
        private ImageView mPersonSign;
        private ImageView mPandaSign;
        private TextView mPandaTv;
        private ImageView mPersonalBackImg;
        private TextView mRegister;

        public MyViewHolder(View v){
            mHudongSign = (ImageView) v.findViewById(R.id.hudong_sign);
            mPersonSign = (ImageView) v.findViewById(R.id.person_sign);
            mPandaSign = (ImageView) v.findViewById(R.id.panda_sign);
            mPandaTv = (TextView) v.findViewById(R.id.tv_panda);
            mPersonalBackImg = (ImageView) v.findViewById(R.id.personal_back_img);
            mRegister = (TextView) v.findViewById(R.id.register);
        }
    }
}
