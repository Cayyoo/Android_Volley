package com.example.volley;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import android.content.Context;

/**
 * 自定义抽象类，包含请求成功和失败后的回调方法
 */
public abstract class VolleyInterface {
    public Context mContext;
    public static Listener<String> mListener;
    public static ErrorListener mErrorListener;

    public VolleyInterface(Context context,Listener<String> listener,ErrorListener errorListener){
        this.mContext=context;
        this.mListener=listener;
        this.mErrorListener=errorListener;
    }

    //请求成功、失败后回调的处理逻辑
    public abstract void onMySuccess(String result);
    public abstract void onMyError(VolleyError error);

    //自定义请求成功的回调方法
    public Listener<String> loadingListener(){
        mListener=new Listener<String>() {

            @Override
            public void onResponse(String arg0) {
                //处理逻辑
                onMySuccess(arg0);
            }
        };
        return mListener;
    }

    //自定义请求失败的回调方法
    public ErrorListener errorListener(){
        mErrorListener=new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                //处理逻辑
                onMyError(arg0);
            }
        };
        return mErrorListener;
    }
}
