package com.example.volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

/**
 * 全局队列
 */
public class MyApplication extends Application {
    //定义全局请求队列
    public static RequestQueue queues;

    @Override
    public void onCreate() {
        super.onCreate();
        //实例化请求队列
        queues=Volley.newRequestQueue(getApplicationContext());
    }

    //自定义方法，获得Get方式的请求队列
    public static RequestQueue getHttpQueues(){
        return queues;
    }

}
