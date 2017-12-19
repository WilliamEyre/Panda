package com.example.laptop.panda_tv.ui.home.personsign.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.laptop.panda_tv.R;
import com.example.laptop.panda_tv.ui.BaseActivity;
import com.example.laptop.panda_tv.ui.MyTitleLayout;
import com.example.laptop.panda_tv.ui.home.personsign.utils.UserManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Laptop on 2017/12/19.
 */
public class MyLoginInfoActivity{
//    private MyTitleLayout myTitleLayout;
//    private ImageView mIcon;
//    private ImageView back;
//    private LinearLayout icon;
//    private TextView tv_name;
//    private ImageView back2;
//    private LinearLayout name;
//    private Button mBtnLogin;
//    private String mNickname, mUserface;/*用户头像跟昵称*/
//    private UserManager mUserManager;
//
//    private Button mSelectPhoto, mMakePhoto, mCancelPhoto;
//    private PopupWindow mPhotoPopWindow;
//    private View mPhotoView;
//    /* 请求识别码 */
//    private static final int CODE_GALLERY_REQUEST = 0;
//    private static final int CODE_CAMERA_REQUEST = 1;
//    private static final int CODE_RESULT_REQUEST = 2;
//    private static final int CODE_CLIP_REQUEST = 3;
//    private LinearLayout mLine;
//    private String mFilename;
//    private String userId;
//    private MultipartBody.Builder builder;
//    private String verifycode;
//    private Uri tempUri;
//    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
//    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
//    private static final int PHOTO_REQUEST_CUT = 3;// 结果
//    private static final int a = 0;
//
//
//    /* 头像名称 */
//    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
//    private File tempFile;
//
//    @Override
//    protected int getLayout() {
//        return R.layout.activity_my_login_info;
//    }
//
//    @Override
//    protected void initView() {
//
//        mUserManager = UserManager.getInstance();
//
//        myTitleLayout = (MyTitleLayout) findViewById(R.id.myTitleLayout);
//        mIcon = (ImageView) findViewById(R.id.img_icon);
//        back = (ImageView) findViewById(R.id.back);
//        icon = (LinearLayout) findViewById(R.id.icon);
//        tv_name = (TextView) findViewById(R.id.tv_name);
//        back2 = (ImageView) findViewById(R.id.back2);
//        name = (LinearLayout) findViewById(R.id.name);
////        mBtnLogin = (Button) findViewById(btn_login);
//        mLine = (LinearLayout) findViewById(R.id.line);
//
//        mBtnLogin.setOnClickListener(this);
//        icon.setOnClickListener(this);
//    }
//
//    @Override
//    protected void initData() {
//
//        myTitleLayout.initViewsVisible(true, false, true, false, false, false);
//        myTitleLayout.setPandaTv("个人信息");
//
//        Intent intent = getIntent();
//        mNickname = intent.getStringExtra("mNickname");//获取个人中心的昵称和头像
//
//        mUserface = intent.getStringExtra("mUserface");
//
//
//        String nickName = mUserManager.getNickName();
//
//        if (nickName != null) {
//            mNickname = nickName;
//            tv_name.setText(mNickname);
//        }
//        Glide.with(this).load(mUserface).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIcon);
//
//    }
//
//    @Override
//    protected void initAdapter() {
//
//    }
//
//    @Override
//    protected void initListener() {
//        myTitleLayout.setOnPersonalBackImgListener(new MyTitleLayout.PersonalBackImgListener() {
//            @Override
//            public void onPersonalBackImgClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_login:
//
//                mUserManager.clearUser();
//
//                finish();
//
//                break;
//            case R.id.icon:
//
//                showPopWindow();
//
//                break;
//        }
//    }
//
//    private void showPopWindow() {
//        mPhotoView = LayoutInflater.from(MyLoginInfoActivity.this).inflate(R.layout.photo_pop, null);
//        mPhotoPopWindow = new PopupWindow(mPhotoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
////        mSelectPhoto = mPhotoView.findViewById(btn_selectphoto);
////        mMakePhoto = mPhotoView.findViewById(btn_makeaphoto);
////        mCancelPhoto = mPhotoView.findViewById(btn_photocancel);
//
//        Window window = getWindow();
//        window.setGravity(Gravity.BOTTOM);
//
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.alpha = 0.5f;
//        getWindow().setAttributes(params);
//
//        mPhotoPopWindow.showAtLocation(mLine, Gravity.BOTTOM, 0, 0);
//
//        mSelectPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gallery(v);
//
//            }
//        });
//
//        mMakePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                camera(v);
//            }
//        });
//
//        CancelPhoto();
//
//    }
//
//    /*
//     * 从相册获取
//     */
//    public void gallery(View view) {
//        // 激活系统图库，选择一张图片
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
//        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
//    }
//    /*
//     * 从相机获取
//     */
//    public void camera(View view) {
//        // 激活相机
//        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//        // 判断存储卡是否可以用，可用进行存储
//        if (hasSdcard()) {
//            tempFile = new File(Environment.getExternalStorageDirectory(),PHOTO_FILE_NAME);
//            // 从文件中创建uri
//            Uri uri = Uri.fromFile(tempFile);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        }
//        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
//        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
//    }
//    /*
//     * 剪切图片
//     */
//    private void crop(Uri uri) {
//        // 裁剪图片意图
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        // 裁剪框的比例，1：1
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        // 裁剪后输出图片的尺寸大小
//        intent.putExtra("outputX", 250);
//        intent.putExtra("outputY", 250);
//
//        intent.putExtra("outputFormat", "JPEG");// 图片格式
//        intent.putExtra("noFaceDetection", true);// 取消人脸识别
//        intent.putExtra("return-data", true);
//        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
//        startActivityForResult(intent, PHOTO_REQUEST_CUT);
//    }
//    /*
//     *   判断sdcard是否被挂载
//     */
//    private boolean hasSdcard() {
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PHOTO_REQUEST_GALLERY) {
//            // 从相册返回的数据
//            if (data != null) {
//                // 得到图片的全路径
//                Uri uri = data.getData();
//                crop(uri);
//            }
//
//        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
//            // 从相机返回的数据
//            if (hasSdcard()) {
//                crop(Uri.fromFile(tempFile));
//            } else {
//                Toast.makeText(MyLoginInfoActivity.this, "未找到存储卡，无法存储照片！",Toast.LENGTH_SHORT).show();
//            }
//
//        } else if (requestCode == PHOTO_REQUEST_CUT) {
//            // 从剪切图片返回的数据
//            if (data != null) {
//                Bitmap bitmap = data.getParcelableExtra("data");
//                uploadPic(bitmap);
//                mIcon.setImageBitmap(bitmap);
//                mPhotoPopWindow.dismiss();
//            }
//            try {
//                // 将临时文件删除
//                tempFile.delete();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void CancelPhoto() {
//        mCancelPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPhotoPopWindow.dismiss();
//                WindowManager.LayoutParams params = getWindow().getAttributes();
//                params.alpha = 1f;
//                getWindow().setAttributes(params);
//            }
//        });
//    }
//
//    private void uploadPic(Bitmap bitmap) {
////        final String imagePath = ImageUtils.savePhoto(bitmap, Environment
////                .getExternalStorageDirectory().getAbsolutePath(), String
////                .valueOf(System.currentTimeMillis()));
////        verifycode = mUserManager.getVerifycode();
////        if(imagePath != null){
////            String url="http://my.cntv.cn/intf/napi/api.php";
////            Log.e("TAG","start...");
////            OkHttpClient okHttpClient=new OkHttpClient.Builder().cookieJar(new CookieJar() {
////                List<Cookie> cooKies=new ArrayList<>();
////
////                @Override
////                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
////                    cooKies=cookies;
////                    for (Cookie cookie:cooKies){
////                        if ("verifycode".equals(cookie.name())){
////                            verifycode = cookie.value();
////                        }
////                    }
////                    Log.e("TAG","running...");
////                }
//
//                @Override
//                public List<Cookie> loadForRequest(HttpUrl url) {
//                    return cooKies;
//                }
//            }).build();
//            Log.e("TAG","end...");
//            File file=new File(imagePath);
//            MultipartBody.Builder builder = new MultipartBody.Builder();
//            builder.setType(MultipartBody.FORM);
//            builder.addFormDataPart("client", "ipanda_mobile");
//            builder.addFormDataPart("method", "user.alterUserFace");
//            builder.addFormDataPart("userid", mUserManager.getUserId());
//            builder.addFormDataPart("facefile", file.getName(), RequestBody.create(MediaType.parse("image/png"), file));
//            RequestBody body = builder.build();
//            Request request = new Request.Builder()
//                    .url(url)
//                    .addHeader("Referer", "iPanda.Android")
//                    .addHeader("User-Agent", "CNTV_APP_CLIENT_CBOX_MOBILE")
//                    .addHeader("Cookie", "verifycode=" + mUserManager.getVerifycode())
//                    .post(body)
//                    .build();
//
//            okHttpClient.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    Log.e("TAG",mUserManager.getUserId()+"----------上传头像--------失败"+ MyLoginInfoActivity.this.verifycode);
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    String string = response.body().string();
//                    try {
//                        JSONObject jsonObject=new JSONObject(string);
//                        int code = jsonObject.getInt("code");
//                        if(code==0){
//                            String error = jsonObject.getString("error");
//                            Log.e("TAG",mUserManager.getUserId()+"----------上传头像--------成功"+error);
//                        }else if(code==-100){
//                            String error = jsonObject.getString("error");
//
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//    }
}
