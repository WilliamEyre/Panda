package com.example.laptop.panda_tv.ui.home.personsign.activity;

import android.content.Intent;
import android.os.UserManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.laptop.panda_tv.BaseActivity;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.MyTitleLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PersonSignActicity{
//    private MyTitleLayout myTitleLayout;
//    private LinearLayout mGuanKanLiShi,myShouCang,mSetting;
//    private LinearLayout person_have_login_layout;
//    private LinearLayout person_no_login_layout;
//    private ImageView mIcon;
//    private TextView mName,mLogin;
//    private UserManager mUserManager;
//    private String mNickname,mUserface;
//
//    @Override
//    protected int getLayout() {
//        return R.layout.activity_person_sign_acticity;
//    }
//
//    @Override
//    protected void initView() {
//
////        mUserManager = UserManager.getInstance();
//
//        //自定义的标题栏
//        myTitleLayout = (MyTitleLayout) findViewById(R.id.myTitleLayout);
//        myTitleLayout.initViewsVisible(true, false, true, false, false, false);
//        //获取控件
//        mGuanKanLiShi = (LinearLayout) findViewById(R.id.mGuanKanLiShi);
//        myShouCang = (LinearLayout) findViewById(R.id.myShouCang);
//        mSetting = (LinearLayout) findViewById(R.id.mSetting);
//        mIcon = (ImageView) findViewById(R.id.img_icon);
//        mName = (TextView) findViewById(R.id.tv_name);
//        mLogin = (TextView) findViewById(R.id.tv_login);
//        person_have_login_layout = (LinearLayout) findViewById(R.id.person_have_login_layout);
//        person_no_login_layout = (LinearLayout) findViewById(R.id.person_no_login_layout);
//    }
//
//    @Override
//    protected void initData() {
//        //标题栏设置值
//        myTitleLayout.setPandaTv("个人中心");
//
//    }
//
//    @Override
//    protected void initAdapter() {
//
//    }
//
//
//    /**
//     * 标题栏返回的监听
//     */
//    @Override
//    protected void initListener() {
//        myTitleLayout.setOnPersonalBackImgListener(new MyTitleLayout.PersonalBackImgListener() {
//            @Override
//            public void onPersonalBackImgClick(View v) {
//                finish();
//            }
//        });
//
//        /**
//         * 跳转注册
//         */
//
//        person_no_login_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PersonSignActicity.this, MyLoginActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        /**
//         * 跳转个人信息
//         */
//        person_have_login_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(PersonSignActicity.this,MyLoginInfoActivity.class);
//                intent.putExtra("mNickname", mNickname);
//                intent.putExtra("mUserface", mUserface);
//                startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //如果已经登录
//        if (mUserManager.isUserLogged()) {
//            //昵称跟头像显示
//            person_have_login_layout.setVisibility(View.VISIBLE);
//            //登录隐藏
//            person_no_login_layout.setVisibility(View.GONE);
//            //获取到保存在本地的昵称跟头像
////            mNickname = mUserManager.getNickName();
////            mUserface = mUserManager.getUserFace();
//            //设置值
//            mName.setText(mNickname);
//            Glide.with(this).load(mUserface).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIcon);
//
////            if (mUserManager.isUserInfoRetrieved()) {
////                return;
////            }
//            //路径
////            String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
////                    + "cbox_mobile" + "&method=" + "user.getNickNameAndFace"
////                    + "&userid=" + mUserManager.getUserId();
//            //网络请求
//            OkHttpClient okHttpClient=new OkHttpClient();
//            Request request = new Request.Builder().url(url).build();
//            okHttpClient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(okhttp3.Call call, IOException e) {
//
//                }
//
//
//
//                @Override
//                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                    final String string = response.body().string();//请求返回的数据
//                    MyApplication.context.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //将请求到的数据返回在主线程进行解析
//                            JSONObject jo = null;
//                            try {
//                                jo = new JSONObject(string);
//                                String code = jo.getString("code");
//                                if (code.equals("0")) {
//                                    if (jo.has("content")) {
//                                        //获取到对象
//                                        JSONObject contentJSONObject = jo.getJSONObject("content");
//                                        //判断json串中是否有nickname这个字段,有则取出
//                                        if (contentJSONObject.has("nickname")) {
//                                            mNickname = contentJSONObject.getString("nickname");
//                                        }
//                                        //判断json串中是否有userface这个字段,有则取出
//                                        if (contentJSONObject.has("userface")) {
//                                            mUserface = contentJSONObject.getString("userface");
//                                        }
//                                        //将取到的值，保存到本地
//                                        mUserManager.saveUserFace(mUserface);
//                                        //由于已经请求过用户信息，所以返回true
//                                        mUserManager.setUserInfoRetrieved(true);
//                                        //再次设置值
//                                        mName.setText(mNickname);
//                                        Glide.with(PersonSignActicity.this).load(mUserface).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIcon);
//                                    }
//                                }
//                            }catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                }
//            });
//
//        }else{
//            //昵称跟头像隐藏
//            person_have_login_layout.setVisibility(View.GONE);
//            //点击登录显示
//            person_no_login_layout.setVisibility(View.VISIBLE);
//        }
//    }
}
