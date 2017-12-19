package com.example.laptop.panda_tv.ui.home.personsign.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.BaseFragment;
import com.example.laptop.panda_tv.ui.MyApplication;
import com.example.laptop.panda_tv.ui.home.personsign.activity.PersonAgreeMentActivity;
import com.example.laptop.panda_tv.ui.home.personsign.activity.PersonSignActicity;
import com.example.laptop.panda_tv.ui.home.personsign.utils.UserManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Laptop on 2017/12/19.
 */
public class PhoneRegisterFragment extends BaseFragment implements View.OnFocusChangeListener, View.OnClickListener {
    private EditText mEtPhone;
    private TextView mHintPhone;
    private EditText mEtImgCode;
    private ImageView mImgCode;
    private TextView mHintImageCode;

    private EditText mEtPhoneCode;
    private TextView mTvPhoneCode;
    private TextView mHintPhoneCode;
    private EditText mEtPassword;
    private String imgurl = "http://reg.cntv.cn/simple/verificationCode.action";
    public String JSESSIONID = null;
    private OkHttpClient ohc;
    private Request build;
    private CheckBox mCbPhoneRegister;
    private TextView mTvPhoneRegister;
    private TextView mHintPassword;
    private Button mBtnPhoneRegister;
    private Request request;

    private String mUserId;
    private String mUserSeqId;
    private String uct;
    private String verifycode;
    private String mNickName;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case 109:

                    UserManager.getInstance().saveNickName(mNickName);
                    UserManager.getInstance().saveUserId(mUserSeqId);
                    Intent intent = new Intent(getActivity(), PersonSignActicity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();

                    break;
                case 107:

                    getUserTicket();

                    break;
            }


        }
    };



    @Override
    protected int getLayout() {
        return R.layout.fragment_phone_register;
    }

    @Override
    protected void initView(View view) {

//        mEtPhone = (EditText) view.findViewById(et_phone);
//        mHintPhone = (TextView) view.findViewById(hint_phone);
        mEtImgCode = (EditText) view.findViewById(R.id.et_imgCode);
        mImgCode = (ImageView) view.findViewById(R.id.img_imgCode);
        mHintImageCode = (TextView) view.findViewById(R.id.hint_imagecode);
        mEtPhoneCode = (EditText) view.findViewById(R.id.et_phoneCode);
        mTvPhoneCode = (TextView) view.findViewById(R.id.tv_phoneCode);
        mHintPhoneCode = (TextView) view.findViewById(R.id.hint_phoneCode);
        mEtPassword = (EditText) view.findViewById(R.id.et_password);
        mCbPhoneRegister = (CheckBox) view.findViewById(R.id.cb_phoneRegister);
        mTvPhoneRegister = (TextView) view.findViewById(R.id.tv_phoneRegister);
        mHintPassword = (TextView) view.findViewById(R.id.hint_password);
        mBtnPhoneRegister = (Button) view.findViewById(R.id.btn_phoneRegister);

        mEtPhone.setOnFocusChangeListener(this);
        mEtImgCode.setOnFocusChangeListener(this);
        mEtPhoneCode.setOnFocusChangeListener(this);
        mEtPassword.setOnFocusChangeListener(this);
        mImgCode.setOnClickListener(this);
        mTvPhoneCode.setOnClickListener(this);
        mTvPhoneRegister.setOnClickListener(this);
        mBtnPhoneRegister.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.et_phone:

                    mHintPhone.setText("");

                    break;

                case R.id.et_imgCode:

                    if (!judgePhone()) {
                        return;
                    }
                    mHintImageCode.setText("");

                    break;
                case R.id.et_phoneCode:

                    if (!judageImgCaptcha()) {
                        return;
                    }
                    mEtPhoneCode.setText("");

                    break;
                case R.id.et_password:

                    if (!judagePhoneCaptcha()) {
                        return;
                    }
                    mEtPassword.setText("");

                    break;
            }
        }
    }

    /**
     * 检查手机号
     *
     * @return
     */

    private Boolean judgePhone() {
        String phoneString = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            mHintPhone.setText("手机号码不能为空");
            return false;
        }
        if (phoneString.matches("^1[3578]\\d{9}$")) {
            mHintPhone.setText("");
            return true;
        } else {
            mHintPhone.setText("手机格式不正确");
            return false;
        }

    }

    /**
     * 检查图形验证码
     *
     * @return
     */
    private Boolean judageImgCaptcha() {
        String imgCodeString = mEtImgCode.getText().toString().trim();

        if (JSESSIONID == null) {
            mHintImageCode.setText("未获取验证码");
            return false;
        }
        if (TextUtils.isEmpty(imgCodeString) && "".equals(imgCodeString)) {
            mHintImageCode.setText("验证码不能为空");
            return false;
        } else {
            mHintImageCode.setText("");
            return true;
        }
    }

    /**
     * 检查手机验证码
     *
     * @return
     */
    private Boolean judagePhoneCaptcha() {
        String PhoneCodeString = mEtPhoneCode.getText().toString().trim();

        if (TextUtils.isEmpty(PhoneCodeString)) {
            mHintPhoneCode.setText("验证码不能为空");
            return false;
        } else {
            mHintPhoneCode.setText("");
            return true;
        }
    }

    /**
     * 检查协议
     *
     * @return
     */
    private boolean judageXieyi() {

        if (!mCbPhoneRegister.isChecked()) {
            mTvPhoneRegister.setText("未勾选《央视网网络服务使用协议》");
            return false;
        } else {
            mTvPhoneRegister.setText("");
            return true;
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        getImgCaptcha();

    }

    /**
     * 获取图片验证码
     */
    private void getImgCaptcha() {
        if (!isConnected()) {
            Toast.makeText(getActivity(), "网络无连接", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpClient ohc = new OkHttpClient.Builder().cookieJar(new CookieJar() {

            private List<Cookie> cookieList = new ArrayList<Cookie>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieList = cookies;
                for (Cookie cookie : cookies) {
                    if ("JSESSIONID".equals(cookie.name())) {

                        JSESSIONID = cookie.value();
                    }

                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return cookieList;
            }
        }).build();

        Request request = new Request.Builder().url(imgurl).build();
        ohc.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final byte[] byte_image = response.body().bytes();

                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //使用BitmapFactory工厂，把字节数组转化为bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(byte_image, 0, byte_image.length);
                        //通过imageview，设置图片
                        mImgCode.setImageBitmap(bitmap);//ImageView 显示验证码的组件
                    }
                });

            }
        });
    }

    /**
     * 获取验证码
     *
     * @param
     */
    private void getCaptcha() {

        if (!isConnected()) {
            Toast.makeText(getActivity(), "网络无连接", Toast.LENGTH_SHORT).show();
            return;
        }
        String phoneString = mEtPhone.getText().toString().trim();
        String phoneimgCode = mEtImgCode.getText().toString().trim();
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
        String from = "http://cbox_mobile.regclientuser.cntv.cn";


        ohc = new OkHttpClient();

        FormBody.Builder body = new FormBody.Builder();
        body.add("method", "getRequestVerifiCodeM");
        body.add("mobile", phoneString);
        body.add("verfiCodeType", "1");
        body.add("verificationCode", phoneimgCode);

        try {
            build = new Request.Builder()
                    .url(url)
                    .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .addHeader("Cookie", "JSESSIONID=" + JSESSIONID)
                    .post(body.build())
                    .build();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ohc.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "请求失败:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (string.endsWith("success")) {
                            Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                        } else if (string.endsWith("registered")) {
                            Toast.makeText(getActivity(), "您的手机号已注册", Toast.LENGTH_SHORT).show();
                        } else if (string.endsWith("sendagain")) {
                            Toast.makeText(getActivity(), "三分钟内只能获取一次", Toast.LENGTH_SHORT).show();
                        } else if (string.endsWith("ipsendagain")) {
                            Toast.makeText(getActivity(), "同一IP用户请求校验码超过5次", Toast.LENGTH_SHORT).show();
                        } else if (string.endsWith("mobileoften")) {
                            Toast.makeText(getActivity(), "同一手机号用户请求校验码超过3次", Toast.LENGTH_SHORT).show();
                        } else if (string.endsWith("mobilecodeerror")) {
                            Toast.makeText(getActivity(), "验证码不正确", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * 检查密码
     *
     * @return
     */
    private boolean judagePassword() {
        String passwordString = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            mHintPassword.setText("密码不能为空");
            return false;
        } else if (passwordString.length() < 6 || passwordString.length() > 16) {
            mHintPassword.setText("密码仅限6-16个字符");
            return false;
        } else {
            mHintPassword.setText("");
            return true;
        }
    }

    /***
     * 手机号注册的请求
     *
     * @param
     * @return
     */
    private void sendPhoneRegistHttp() {
        if (!isConnected()) {
            Toast.makeText(getActivity(), "网络无连接", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://reg.cntv.cn/regist/mobileRegist.do";
        String mPhoneNumber = mEtPhone.getText().toString().trim();
        String mPhoneCode = mEtPhoneCode.getText().toString().trim();
        String mPassWord = mEtPassword.getText().toString();


        OkHttpClient ohc = new OkHttpClient();

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("method", "saveMobileRegisterM");
        builder.add("mobile", mPhoneNumber);
        builder.add("verfiCode", mPhoneCode);

        try {
            builder.add("passWd", URLEncoder.encode(mPassWord, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        builder.add("verfiCodeType", "1");

        try {
            builder.add("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .post(builder.build())
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ohc.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (string.endsWith("success")) {
                            Toast.makeText(getActivity(), "手机号注册成功", Toast.LENGTH_SHORT).show();

                            goLogin();

                        } else if (string.endsWith("registered")) {
                            Toast.makeText(getActivity(), "该手机号已注册", Toast.LENGTH_SHORT).show();
                        } else if (string.endsWith("error")) {
                            Toast.makeText(getActivity(), "验证码输入有误", Toast.LENGTH_SHORT).show();
                        } else if (string.endsWith("mobilenull")) {
                            Toast.makeText(getActivity(), "手机号为空", Toast.LENGTH_SHORT).show();
                        } else if (string.endsWith("timeout")) {
                            Toast.makeText(getActivity(), "校验码已超过有效时间", Toast.LENGTH_SHORT).show();
                        } else if (string.endsWith("passwordnull")) {
                            Toast.makeText(getActivity(), "密码为空", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });
    }

    /**
     * 登录
     */
    private void goLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String mUserNameString = mEtPhone.getText().toString()
                        .trim();
                String mPassWordString = mEtPassword.getText().toString()
                        .trim();
                try {
                    String from = "https://reg.cntv.cn/login/login.action";
                    String url = from + "?username="
                            + URLEncoder.encode(mUserNameString, "UTF-8")
                            + "&password=" + mPassWordString
                            + "&service=client_transaction" + "&from="
                            + URLEncoder.encode(from, "UTF-8");

                    OkHttpClient ohc = new OkHttpClient.Builder().cookieJar(new CookieJar() {

                        private List<Cookie> cookieList = new ArrayList<Cookie>();

                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookieList = cookies;
                            for (Cookie cookie : cookies) {
                                if ("JSESSIONID".equals(cookie.name())) {
                                    JSESSIONID = cookie.value();
                                } else if ("verifycode".equals(cookie.name())) {
                                    verifycode = cookie.value();
                                } else if ("uct".equals(cookie.name())) {
                                    uct = cookie.value();
                                }
                            }
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            return cookieList;
                        }
                    }).build();
                    Request request = null;
                    try {
                        request = new Request.Builder()
                                .url(url)
                                .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                                .addHeader("User-Agent", URLEncoder.encode(
                                        "CNTV_APP_CLIENT_CYNTV_MOBILE", "UTF-8")).build();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    ohc.newCall(request).enqueue(new Callback() {

                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            final String string = response.body().string();

                            JSONObject jo = null;
                            try {
                                jo = new JSONObject(string);
                                if (jo.getString("errType").equals("0")) {
                                    if (jo.has("user_seq_id")) {
                                        mUserSeqId = jo.getString("user_seq_id");
                                    }
                                    if (jo.has("usrid")) {
                                        mUserId = jo.getString("usrid");
                                    }
                                    mHandler.sendEmptyMessage(107);
                                } else {
                                    String errMsg = jo.getString("errMsg");

                                    Message msg = mHandler
                                            .obtainMessage(100);
                                    msg.obj = errMsg;
                                    mHandler.sendMessage(msg);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 获取昵称
     */
    private void getUserTicket() {
        new Thread(new Runnable() {

            private Request body;

            @Override
            public void run() {
                String client = "http://cbox_mobile.regclientuser.cntv.cn";
                String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
                        + "cbox_mobile" + "&method=" + "user.getNickName"
                        + "&userid=" + mUserSeqId;

                OkHttpClient ohc = new OkHttpClient();
                try {
                    body = new Request.Builder()
                            .url(url)
                            .addHeader("Referer", URLEncoder.encode(client, "UTF-8"))
                            .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                            .addHeader("Cookie", "verifycode=" + verifycode)
                            .build();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ohc.newCall(body).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String string = response.body().string();
                        MyApplication.context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                JSONObject jo = null;
                                try {
                                    jo = new JSONObject(string);

                                    if (jo.getString("code").equals("0")) {
                                        if (jo.has("content")) {
                                            JSONObject contentJSONObject = jo.getJSONObject("content");
                                            if (contentJSONObject.has("nickname")) {
                                                mNickName = contentJSONObject.getString("nickname");
                                            } else {
                                                mNickName = "default";
                                            }
                                        }
                                        mHandler.sendEmptyMessage(109);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });
            }
        }).start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_imgCode:

                if (!isConnected()) {
                    Toast.makeText(getActivity(), "网络无连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取图片验证码
                getImgCaptcha();

                break;
            case R.id.tv_phoneCode:

                if (!isConnected()) {
                    Toast.makeText(getActivity(), "网络无连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                mEtPhone.clearFocus();
                mEtImgCode.clearFocus();
                mEtPhoneCode.clearFocus();

                String phoneString = mEtPhone.getText().toString().trim();
                String imgCode = mEtImgCode.getText().toString().trim();

                if (TextUtils.isEmpty(imgCode)) {
                    mHintImageCode.setText("图片验证码输入有误");
                }
                if (!judgePhone()) {
                    return;
                }
                if (!judageImgCaptcha()) {
                    return;
                } else {

                    mHintPhone.setText("");
                    mHintImageCode.setText("");

                    //获取验证码
                    getCaptcha();
                }


                break;
            case R.id.tv_phoneRegister:

                Intent intent = new Intent(getActivity(), PersonAgreeMentActivity.class);
                startActivity(intent);

                break;
            case R.id.btn_phoneRegister:

                mEtPhone.clearFocus();
                mEtImgCode.clearFocus();
                mEtPhoneCode.clearFocus();
                mEtPassword.clearFocus();

                if (!isConnected()) {
                    Toast.makeText(getActivity(), "网络无连接", Toast.LENGTH_SHORT).show();
                    return;
                }

                //点击注册
                if (!judgePhone()) {
                    return;
                }


                if (!judageImgCaptcha()) {
                    return;
                }

                if (!judagePhoneCaptcha()) {

                    return;
                }
                if (!judagePassword()) {
                    return;
                }


                if (!judageXieyi()) {
                    return;
                }

                sendPhoneRegistHttp();

                break;


        }
    }

}
