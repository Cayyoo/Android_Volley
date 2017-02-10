package com.example.volley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


/**
 * 1.Volley的get和post请求方式的使用
 * 2.Volley的网络请求队列建立和取消队列请求
 * 3.Volley与Activity生命周期的联动
 * 4.Volley的简单的二次回调封装
 */
public class MainActivity extends Activity {
    /**
     * Volley请求数据，有3种类型
     * StringRequest
     * JsonObjectRequest
     * JsonArrayRequest
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用Volley的Get或Post方法分别请求数据
        volley_Get();
//		volley_Post();
    }

    //通过onStop()将Volley与Activity的生命周期关联
    @Override
    protected void onStop() {
        super.onStop();
        //cancelAll()方法：通过给定的tag值，将指定的队列全部关闭
        MyApplication.getHttpQueues().cancelAll("Volly_Get_Tag");
    }



    //自定义Volley的Get方法
    private void volley_Get() {
        //测试用url，返回数据类型为Json格式
        String url="http://112.124.22.238:8081/course_api/banner/query?type=1";

        /**
         * 方式一、使用StringRequest请求，未使用Volley的二次封装
         */
//		StringRequest request=new StringRequest(Method.GET, url, new Listener<String>() {
//
//			//数据请求成功的回调方法
//			@Override
//			public void onResponse(String arg0) {
//				//成功后的逻辑
//				Toast.makeText(MainActivity.this, arg0, Toast.LENGTH_SHORT).show();
//				Log.i("Tiger", arg0);
//			}
//		}, new Response.ErrorListener() {
//
//			//数据请求失败的回调方法
//			@Override
//			public void onErrorResponse(VolleyError arg0) {
//				//失败后的处理逻辑
//				Toast.makeText(MainActivity.this, arg0.toString(), Toast.LENGTH_SHORT).show();
//				Log.i("Tiger", arg0.toString());
//			}
//		});
//		//对请求对象设置Tag标签
//		request.setTag("Volly_Get_Tag");
//		//将request添加到全局请求队列
//		MyApplication.getHttpQueues().add(request);

        /**
         * 方式一、使用StringRequest请求，使用Volley的二次封装
         */
        VolleyRequest.RequestGet(this, url, "Volly_Get_Tag", new VolleyInterface(this,VolleyInterface.mListener,VolleyInterface.mErrorListener) {

            //数据请求成功的回调方法
            @Override
            public void onMySuccess(String result) {
                //成功后的逻辑
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                Log.i("Tiger", result);
            }

            //数据请求失败的回调方法
            @Override
            public void onMyError(VolleyError error) {
                //失败后的处理逻辑
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("Tiger", error.toString());
            }
        });


        /**
         * 方式二、使用JsonObjectRequest请求
         * JsonObjectRequest与JsonArrayRequest类似
         */
//		JsonObjectRequest objectRequest=new JsonObjectRequest(Method.GET, url, null, new Listener<JSONObject>() {
//
//			//数据请求成功的回调方法
//			@Override
//			public void onResponse(JSONObject arg0) {
//				//成功后的逻辑，此处返回类型为Json，可直接使用
//				Toast.makeText(MainActivity.this, arg0.toString(), Toast.LENGTH_SHORT).show();
//				Log.i("Tiger", arg0.toString());
//				
//			}
//		}, new Response.ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError arg0) {
//				//失败后的逻辑
//				Toast.makeText(MainActivity.this, arg0.toString(), Toast.LENGTH_SHORT).show();
//				Log.i("Tiger", arg0.toString());
//			}
//		});
//		//对请求对象设置Tag标签
//		objectRequest.setTag("Volly_Get_Tag");
//		//将request添加到全局请求队列
//		MyApplication.getHttpQueues().add(objectRequest);
    }


    //自定义Volley的Post方法
    private void volley_Post(){
        //测试用url，返回数据类型为Json格式
        //http://apis.juhe.cn/mobile/get?phone=13429667914&key=335adcc4e891ba4e4be6d7534fd54c5d
        String url="http://apis.juhe.cn/mobile/get?";

        /**
         * 方式一、使用StringRequest请求，未使用Volley的二次封装
         */
//		StringRequest request=new StringRequest(Method.POST, url, new Listener<String>() {
//
//			//数据请求成功的回调方法
//			@Override
//			public void onResponse(String arg0) {
//				//成功后的逻辑
//				Toast.makeText(MainActivity.this, arg0, Toast.LENGTH_SHORT).show();
//				Log.i("Tiger", arg0);
//			}
//		}, new Response.ErrorListener() {
//
//			//数据请求失败的回调方法
//			@Override
//			public void onErrorResponse(VolleyError arg0) {
//				//失败后的处理逻辑
//				Toast.makeText(MainActivity.this, arg0.toString(), Toast.LENGTH_SHORT).show();
//				Log.i("Tiger", arg0.toString());
//			}
//		}){
//			//getParams()方法：用户在Volley中使用Post方式请求数据中参数的传递
//			@Override
//			protected Map<String, String> getParams() throws AuthFailureError {
//				//定义一个Map
//				Map<String,String> hashMap=new HashMap<String,String>();
//				//将传递的参数添加到HashMap中
//				hashMap.put("phone", "13429667914");
//				hashMap.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");
//				return hashMap;
//			}
//		};
//		//对请求对象设置Tag标签
//		request.setTag("Volly_Post_Tag");
//		//将request添加到全局请求队列
//		MyApplication.getHttpQueues().add(request);


        /**
         * 方式二、使用JsonObjectRequest请求
         * JsonObjectRequest与JsonArrayRequest类似
         */

        //定义map
        HashMap<String,String> map=new HashMap<String,String>();
        //将传递的参数添加到map中
        map.put("phone", "13429667914");
        map.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");
        //实例化JSONObject对象
        JSONObject object=new JSONObject(map);

        JsonObjectRequest objectRequest=new JsonObjectRequest(Method.POST, url, object, new Listener<JSONObject>() {

            //数据请求成功的回调方法
            @Override
            public void onResponse(JSONObject arg0) {
                //成功后的逻辑，此处返回类型为Json，可直接使用
                Toast.makeText(MainActivity.this, arg0.toString(), Toast.LENGTH_SHORT).show();
                Log.i("Tiger", arg0.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                //失败后的逻辑
                Toast.makeText(MainActivity.this, arg0.toString(), Toast.LENGTH_SHORT).show();
                Log.i("Tiger", arg0.toString());
            }
        });
        //对请求对象设置Tag标签
        objectRequest.setTag("Volly_Post_Tag");
        //将request添加到全局请求队列
        MyApplication.getHttpQueues().add(objectRequest);

    }

}
