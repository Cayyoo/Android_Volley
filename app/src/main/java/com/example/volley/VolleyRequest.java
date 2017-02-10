package com.example.volley;

import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;

import android.content.Context;

/**
 * Volley的简单的二次回调封装
 */
public class VolleyRequest {
    public static StringRequest stringRequest;
    public static Context context;

    //Get方法
    public static void RequestGet(Context mContext,String url,String tag,VolleyInterface vif){
        //取消请求队列，以防止消耗内存
        MyApplication.getHttpQueues().cancelAll(tag);
        //实例化stringRequest
        stringRequest=new StringRequest(Method.GET, url, vif.loadingListener(), vif.errorListener());
        //设置tag标签
        stringRequest.setTag(tag);
        //将stringRequest添加到请求队列
        MyApplication.getHttpQueues().add(stringRequest);
        //启动队列
        MyApplication.getHttpQueues().start();

    }

    //Post方法
    public static void RequestPost(Context mContext,String url,String tag,final Map<String,String> params,VolleyInterface vif){
        //取消请求队列，以防止消耗内存
        MyApplication.getHttpQueues().cancelAll(tag);
        stringRequest=new StringRequest(url,vif.loadingListener(),vif.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        //设置tag标签
        stringRequest.setTag(tag);
        //将stringRequest添加到请求队列
        MyApplication.getHttpQueues().add(stringRequest);
        //启动队列
        MyApplication.getHttpQueues().start();
    }
}
