package com.example.laptop.panda_tv.ui.mvp.okhttp;

import com.example.laptop.panda_tv.ui.MyApplication;
import com.example.laptop.panda_tv.ui.mvp.model.CallBackListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Laptop on 2017/12/19.
 */
public class OkHttpUtils implements IHttp{
    private static OkHttpUtils okHttpUtils;

    private static OkHttpClient okHttpClient;

    private OkHttpUtils() {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }


    @Override
    public <T> void get(String url, final CallBackListener<T> listener) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onError(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                MyApplication.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Gson gson = new Gson();
                        Type[] types =  listener.getClass().getGenericInterfaces();
                        Type[] type =  ((ParameterizedType) types[0]).getActualTypeArguments();
                        Type entity = type[0];
                        T t = gson.fromJson(string,entity);
                        listener.onSuccess(t);
                    }
                });
            }
        });

    }

    @Override
    public <T> void post(String url, CallBackListener<T> listener) {

    }
}
