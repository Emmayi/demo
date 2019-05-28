package cn.edu.bupt.demo.controller;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zy
 * @date 2019/5/28 上午11:05
 */
public class HttpLogin {
    static private Cookie ck;
    private String host = "39.104.84.131";
    static private String session ;
    public static String id;
//    public String deviceToken;

    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private static final MediaType js = MediaType.parse("application/json; charset=utf-8");

    ///创建okHttpClient对象
    private OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url.host(), cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            })
            .build();


    /*
    登录时的post请求
     */
    protected void httplogin() throws Exception{
        cookieStore.clear();
        //请求体
        RequestBody bodyLogin = RequestBody.create(js, "{\"username\":\"czxstc200@gmail.com\",\"password\":\"password\"}");

        //创建一个Request Request是OkHttp中访问的请求，Builder是辅助类。Response即OkHttp中的响应。
        final Request requestLogin = new Request.Builder()
                .url("http://39.104.84.131/api/user/login")
                .header("Accept","text/plain, */*; q=0.01")
                .addHeader("Connection","keep-alive")
                .addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                .addHeader("Content-Type","application/json; charset=UTF-8")
                .post(bodyLogin)
                .build();

        Response response = mOkHttpClient.newCall(requestLogin).execute();
        if (response.isSuccessful()) {
//            String result = response.body().string();

            Headers headers = response.headers();
            System.out.println("login header " + headers);

            ck = cookieStore.get(host).get(0);

            String sessionStr = ck.toString();
            session = sessionStr.substring(0,sessionStr.indexOf(";"));
            System.out.println("login cookie is : " + ck);
            System.out.println("login session is  :" + session);
        }


//        //得到一个call对象
//        Call call = mOkHttpClient.newCall(requestLogin);
//        //请求加入调度
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                Headers headers = response.headers();
//                System.out.println("login header " + headers);
//
//                ck = cookieStore.get(host).get(0);
//
//                String sessionStr = ck.toString();
//                session = sessionStr.substring(0,sessionStr.indexOf(";"));
//
//                System.out.println("login cookie is : " + ck);
//                System.out.println("login session is  :" + session);
//            }
//        });

    }

    /*
    获取设备的get请求
     */
    protected String findDevice()throws Exception {

        //创建一个Request Request是OkHttp中访问的请求，Builder是辅助类。Response即OkHttp中的响应。
        Request requestCreate = new Request.Builder()
                .url("http://39.104.84.131/api/device/alldevices?limit=1000")
                .get()
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Connection", "keep-alive")
//                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .addHeader("Cookie", session.toString())
                .build();
        //得到一个call对象
        Response response = mOkHttpClient.newCall(requestCreate).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            return result;
        }
        return null;

    }
    /*
   获取设备属性的get请求
    */
    protected String findAttributes(String id)throws Exception {

        //创建一个Request Request是OkHttp中访问的请求，Builder是辅助类。Response即OkHttp中的响应。
        Request requestCreate = new Request.Builder()
                .url("http://39.104.84.131/api/data/getKeyAttribute/"+id)
                .get()
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Connection", "keep-alive")
//                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .addHeader("Cookie", session.toString())
                .build();
        //得到一个call对象
        Response response = mOkHttpClient.newCall(requestCreate).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            return result;
        }
        return null;

    }

}
