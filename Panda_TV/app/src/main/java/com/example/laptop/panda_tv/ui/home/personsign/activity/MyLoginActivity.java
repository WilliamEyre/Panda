package com.example.laptop.panda_tv.ui.home.personsign.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laptop.panda_tv.BaseActivity;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.MyApplication;
import com.example.laptop.panda_tv.ui.MyTitleLayout;
import com.example.laptop.panda_tv.ui.home.personsign.utils.UserManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class MyLoginActivity extends BaseActivity{
    private MyTitleLayout myTitleLayout;
    private RadioButton mWeiXin,mQQ,mWeiBo;
    private EditText mEtName,mEtPassword;
    private TextView mTvPassword,mHintName,mHintPassword;
    private Button mBtnLogin;
    public static final String LOGIN_URL = "https://reg.cntv.cn/login/login.action";
    private String mUserSeqId,mUserId,mNickName,verifycode;


    @Override
    protected int getLayout() {
        return R.layout.activity_my_login;
    }

    @Override
    protected void initView() {
        //获取控件
        myTitleLayout = (MyTitleLayout) findViewById(R.id.myTitleLayout);
        mWeiXin = (RadioButton) findViewById(R.id.rb_WeiXin);
        mQQ = (RadioButton) findViewById(R.id.rb_QQ);
        mWeiBo = (RadioButton) findViewById(R.id.rb_WeiBo);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mTvPassword = (TextView) findViewById(R.id.tv_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mHintName = (TextView) findViewById(R.id.hint_name);
        mHintPassword = (TextView) findViewById(R.id.hint_password);
    }

    @Override
    protected void initData() {
        //设置标题栏
        myTitleLayout.initViewsVisible(true, false, true, false, false, true);
        myTitleLayout.setPandaTv("登录");
        //忘记密码设置下划线
        mTvPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    protected void initAdapter() {

    }

    /**
     * 获取昵称
     * @throws UnsupportedEncodingException
     */
    private void getUserTicket() throws UnsupportedEncodingException {
        String client = "http://cbox_mobile.regclientuser.cntv.cn";
        String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
                + "cbox_mobile" + "&method=" + "user.getNickName"
                + "&userid=" + mUserSeqId;
        OkHttpClient ohc = new OkHttpClient();

        Request request = new Request.Builder().url(url).addHeader("Referer", URLEncoder.encode(client, "UTF-8"))
                .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                .addHeader("Cookie", "verifycode=" + verifycode)
                .build();

        ohc.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                JSONObject jo = null;
                try {
                    jo = new JSONObject(string);
                    String code = jo.getString("code");
                    if (code.equals("0")) {
                        if (jo.has("content")) {
                            JSONObject contentJSONObject = jo.getJSONObject("content");
                            if (contentJSONObject.has("nickname")) {
                                mNickName = contentJSONObject.getString("nickname");
                            } else {
                                mNickName = "default";
                            }
                        }
                        UserManager.getInstance().saveNickName(mNickName);
                        UserManager.getInstance().saveUserId(mUserSeqId);
                        UserManager.getInstance().saveVerifycode(verifycode);
                        Intent intent = new Intent(MyLoginActivity.this,PersonSignActicity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String codeErrorString = jo.getString("error");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 登录
     */
    private void goLogin() {
        //获取到输入的账号跟密码
        String userName = mEtName.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        //创建OkHttpClient对象
        OkHttpClient ohc = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            List<Cookie> cookieList = new ArrayList<>();
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieList = cookies;
                for (Cookie cookie : cookies) {
                    if ("verifycode".equals(cookie.name())) {
                        verifycode = cookie.value();
                    }

                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return cookieList;
            }
        }).build();
        //创建请求体
        FormBody.Builder builder = new FormBody.Builder();
        //给请求体设置值
        builder.add("from", LOGIN_URL);
        builder.add("service", "client_transaction");
        builder.add("username", userName);
        builder.add("password", password);
        //创建Request对象
        Request request = new Request.Builder()
                //设置路径
                .url(LOGIN_URL)
                //设置请求头
                .addHeader("Referer",LOGIN_URL)
                .addHeader("User-Agent","CNTV_APP_CLIENT_CYNTV_MOBILE")
                //设置请求体
                .post(builder.build())
                .build();
        //开启异步线程
        ohc.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //吐司失败的原因
                String message = e.getMessage();
                Toast.makeText(MyLoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                final String string = response.body().string();//返回字符串
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissLoadDialog();
                        JSONObject jo = null;
                        try {
                            jo = new JSONObject(string);
                            String errType = jo.getString("errType");
                            if (errType.equals("0")) {
                                Toast.makeText(MyLoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                if (jo.has("user_seq_id")) {
                                    mUserSeqId = jo.getString("user_seq_id");
                                    try {
                                        getUserTicket();
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (jo.has("usrid")) {
                                    mUserId = jo.getString("usrid");
                                }
                            } if (errType.equals("105")) {
                                Toast.makeText(MyLoginActivity.this, "密码错误，请重输", Toast.LENGTH_SHORT).show();
                            }
                            if (errType.equals("104")) {
                                Toast.makeText(MyLoginActivity.this, "该账号未注册。", Toast.LENGTH_SHORT).show();
                            }
                            if (errType.equals("106")) {
                                Toast.makeText(MyLoginActivity.this, "很抱歉，您的账号还没有激活，请激活后再登录。", Toast.LENGTH_SHORT).show();
                            }
                            if (errType.equals("102")) {
                                Toast.makeText(MyLoginActivity.this, "缺少参数", Toast.LENGTH_SHORT).show();
                            }else {
                                String errMsg = jo.getString("errMsg");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        login_login.setText(string);
                    }

                });

            }


        });

    }

    @Override
    protected void initListener() {
        myTitleLayout.setOnPersonalBackImgListener(new MyTitleLayout.PersonalBackImgListener() {
            @Override
            public void onPersonalBackImgClick(View v) {
                finish();
            }
        });

        /**
         * 返回的监听
         */
        myTitleLayout.setOnRegisterListener(new MyTitleLayout.RegisterListener() {
            @Override
            public void onRegisterClick(View v) {
                Intent intent = new Intent(MyLoginActivity.this, MyRegisterActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 登录
         */
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtPassword.clearFocus();

                if (!isConnected()) {
                    Toast.makeText(MyLoginActivity.this, "网络无连接", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!checkEmailAndPhone()) {
                    return;
                }
                mHintName.setText("");

                if (checkEmpty(mEtPassword) == false) {
                    mHintPassword.setText("密码不能为空");
                    return;
                }
                showLoadingDialog();
                mHintName.setText("");
                mHintPassword.setText("");
                goLogin();
            }
        });

    }



    /**
     * 检查是否为空
     *
     * @param editText
     * @return
     */
    private boolean checkEmpty(EditText editText) {
        String testTxt = editText.getText().toString();
        if (testTxt == null || "".equals(testTxt)) {
            return false;
        }
        return true;
    }


    // 检查邮箱、手机号
    private boolean checkEmailAndPhone() {
        String emailString = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(emailString)) {
            mHintName.setText("邮箱/手机号不能为空");
            return false;
        }
        String tEmail = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        if (emailString.indexOf("@") == -1) {
            tEmail = "^1[3578]\\d{9}$";
        }
        Pattern pattern = Pattern
                .compile(tEmail);
        Matcher matcher = pattern.matcher(emailString);
        if (matcher.matches()) {
            mHintName.setText("");
            return true;
        } else {
            mHintName.setText("邮箱/手机格式不正确");
            return false;
        }
    }
}
