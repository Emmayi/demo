package cn.edu.bupt.demo.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zy
 * @date 2019/5/28 下午6:51
 */
public class HttpUtil {

    @Value("${account.login_url}")
    private void getLogin(String loginUrl) {
        tokenurl = loginUrl ;
    }

    @Value("${account.logout_url}")
    private void getLogout(String logoutUrl) {
        logouturl = logoutUrl ;
    }

    @Value("${account.client_id}")
    private void getClientId(String client_id) {
        Client_id = client_id ;
    }

    @Value("${account.client_secret}")
    private void getClientSecret(String client_secret) {
        Client_secret = client_secret ;
    }

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    private static String tokenurl;
    private static String logouturl;
    private static String Client_id;
    private static String Client_secret;


    public static String sendPostToThingsboard(String url, Map<String,String> headers, JsonObject requestBody, HttpSession session) throws Exception{
        String str ;
        if(requestBody==null){
            str = "";
        }else{
            str = requestBody.toString();
        }
        RequestBody body = RequestBody.create(JSON, str);
        Request.Builder buider = new Request.Builder()
                .url(url)
                .post(body);

        String tocken = (String)session.getAttribute("token");
        if(tocken==null){
            getAccessToken(session);
        }
        tocken = (String)session.getAttribute("token");
        buider.header("Authorization","Bearer "+tocken);

        if(headers!=null){
            for(Map.Entry<String,String> entry:headers.entrySet()){
                buider.header(entry.getKey(),entry.getValue());
            }
        }
        Request request = buider.build();

        return sendRequireToThingsboard(request, session);
    }

    public static Response sendPost(String url, Map<String,String> headers, JsonObject requestBody, HttpSession session) throws Exception{
        String str ;
        if(requestBody==null){
            str = "";
        }else{
            str = requestBody.toString();
        }
        RequestBody body = RequestBody.create(JSON, str);
        Request.Builder buider = new Request.Builder()
                .url(url)
                .post(body);

        String tocken = (String)session.getAttribute("token");
        if(tocken==null){
            getAccessToken(session);
        }
        tocken = (String)session.getAttribute("token");
        buider.header("Authorization","Bearer "+tocken);

        if(headers!=null){
            for(Map.Entry<String,String> entry:headers.entrySet()){
                buider.header(entry.getKey(),entry.getValue());
            }
        }
        Request request = buider.build();

        return sendRequire(request, session);
    }

    public static String sendPutToThingsboard(String url, Map<String,String> headers, JsonObject requestBody,HttpSession session) throws Exception{
        String str ;
        if(requestBody==null){
            str = "";
        }else{
            str = requestBody.toString();
        }
        RequestBody body = RequestBody.create(JSON, str);
        Request.Builder buider = new Request.Builder()
                .url(url)
                .put(body);

        String tocken = (String)session.getAttribute("token");
        if(tocken==null){
            getAccessToken(session);
        }
        tocken = (String)session.getAttribute("token");
        buider.header("Authorization","Bearer "+tocken);

        if(headers!=null){
            for(Map.Entry<String,String> entry:headers.entrySet()){
                buider.header(entry.getKey(),entry.getValue());
            }
        }
        Request request = buider.build();

        return sendRequireToThingsboard(request, session);
    }

    public static Response sendPut(String url, Map<String,String> headers, JsonObject requestBody,HttpSession session) throws Exception{
        String str ;
        if(requestBody==null){
            str = "";
        }else{
            str = requestBody.toString();
        }
        RequestBody body = RequestBody.create(JSON, str);
        Request.Builder buider = new Request.Builder()
                .url(url)
                .put(body);

        String tocken = (String)session.getAttribute("token");
        if(tocken==null){
            getAccessToken(session);
        }
        tocken = (String)session.getAttribute("token");
        buider.header("Authorization","Bearer "+tocken);

        if(headers!=null){
            for(Map.Entry<String,String> entry:headers.entrySet()){
                buider.header(entry.getKey(),entry.getValue());
            }
        }
        Request request = buider.build();

        return sendRequire(request, session);
    }

    public static String sendDeletToThingsboard(String url,HttpSession session) throws Exception{
        Request.Builder buider = new Request.Builder()
                .url(url)
                .delete() ;
        String tocken = (String)session.getAttribute("token");
        if(tocken==null){
            getAccessToken(session);
        }
        tocken = (String)session.getAttribute("token");
        buider.header("Authorization","Bearer "+tocken);
        Request request = buider.build();

        return sendRequireToThingsboard(request, session);
    }

    public static Response sendDelet(String url,HttpSession session) throws Exception{
        Request.Builder buider = new Request.Builder()
                .url(url)
                .delete() ;
        String tocken = (String)session.getAttribute("token");
        if(tocken==null){
            getAccessToken(session);
        }
        tocken = (String)session.getAttribute("token");
        buider.header("Authorization","Bearer "+tocken);
        Request request = buider.build();

        return sendRequire(request, session);
    }

    public static Response sendDelet(String url,HttpSession session,JsonObject requestBody) throws Exception{
        String str;
        if(requestBody==null){
            str = "";
        }else{
            str = requestBody.toString();
        }
        RequestBody body = RequestBody.create(JSON, str);
        Request.Builder buider = new Request.Builder()
                .url(url)
                .delete(body) ;
        String tocken = (String)session.getAttribute("token");
        if(tocken==null){
            getAccessToken(session);
        }
        tocken = (String)session.getAttribute("token");
        buider.header("Authorization","Bearer "+tocken);
        Request request = buider.build();

        return sendRequire(request, session);
    }

    public static String sendGetToThingsboard(String url, Map<String,String> headers, HttpSession session) throws Exception{

        Request.Builder buider = new Request.Builder()
                .url(url)
                .get() ;


        String tocken = (String)session.getAttribute("token");
        if(tocken==null){
            getAccessToken(session);
        }
        tocken = (String)session.getAttribute("token");
        buider.header("Authorization","Bearer "+tocken);

        if(headers!=null){
            for(Map.Entry<String,String> entry:headers.entrySet()){
                buider.header(entry.getKey(),entry.getValue());
            }
        }
        Request request = buider.build();

        return sendRequireToThingsboard(request, session);
    }

    public static Response sendGet(String url, Map<String,String> headers, HttpSession session) throws Exception{

        Request.Builder buider = new Request.Builder()
                .url(url)
                .get() ;


        String tocken = (String)session.getAttribute("token");
        if(tocken==null){
            getAccessToken(session);
        }
        tocken = (String)session.getAttribute("token");
        buider.header("Authorization","Bearer "+tocken);

        if(headers!=null){
            for(Map.Entry<String,String> entry:headers.entrySet()){
                buider.header(entry.getKey(),entry.getValue());
            }
        }
        Request request = buider.build();

        return sendRequire(request, session);
    }

    public static String getAccessToken(HttpSession session){
        String username = (String)session.getAttribute("username");
        String password = (String)session.getAttribute("password");
//        if(username==null|| password ==null) return false;

        String content = "username="+username+"&password="+password;
        RequestBody body = RequestBody.create(FORM, content);
        Request.Builder builder = new Request.Builder()
                .url(tokenurl+"?grant_type=password")
                .post(body);

        byte[] textByte = (Client_id+":"+Client_secret).getBytes();
        String auth = encoder.encodeToString(textByte);
        builder.header("Authorization","Basic "+auth);

        Request request = builder.build();
        try{
            // 第一次获取token
            Response response = execute(request);
            if(response.isSuccessful()){
                String res = response.body().string();
                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                if(!obj.has("error")) {
                    session.setAttribute("token", obj.get("access_token").getAsString());
                    session.setAttribute("refreshToken", obj.get("refresh_token").getAsString());
                    session.setAttribute("tenant_id", obj.get("tenant_id").getAsInt());
                    session.setAttribute("customer_id", obj.get("customer_id").getAsInt());
                }
                return res;
            } else{
                throw new Exception("the first fail!") ;
            }
        }catch (Exception e){
            // 第二次获取token
            try {
                Response response = execute(request);
                String res = response.body().string();
                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                if(!obj.has("error")) {
                    session.setAttribute("token", obj.get("access_token").getAsString());
                    session.setAttribute("refreshToken", obj.get("refresh_token").getAsString());
                    session.setAttribute("tenant_id", obj.get("tenant_id").getAsInt());
                    session.setAttribute("customer_id", obj.get("customer_id").getAsInt());
                }
                return res ;
            } catch (Exception e1) {
                return  "ERROR!";
            }
        }
    }

    public static boolean logout(String token){
        Request.Builder builder = new Request.Builder()
                .url(logouturl)
                .get();

        builder.header("Authorization","bearer"+token);
        Request request = builder.build();
        try {
            // 第一次获取token
            Response response = execute(request);
            if(response.isSuccessful()){
                return true;
            }else{
                throw new Exception("fail to logout!") ;
            }
        }catch (Exception e) {
            return false;
        }
    }

    public static String refreshToken(String refreshToken){

        String content = "refresh_token="+refreshToken;
        RequestBody body = RequestBody.create(FORM, content);

        Request.Builder builder = new Request.Builder()
                .url(tokenurl+"?grant_type=refresh_token")
                .post(body);

        byte[] textByte = (Client_id+":"+Client_secret).getBytes();
        String auth = encoder.encodeToString(textByte);
        builder.header("Authorization","Basic "+auth);

        Request request = builder.build();

        try {
            // 第一次获取token
            Response response = execute(request);
            if(response.isSuccessful()){
                return response.body().string();
            }else{
                throw new Exception("fail to refresh!") ;
            }
        }catch (Exception e) {
            return e.toString();
        }
    }

    /**
     * 发送请求到thingsboard，并确保session中的认证。
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    private static String sendRequireToThingsboard(Request request, HttpSession session) throws Exception{
        Response response = httpClient.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }else if(response.code() == 401){
            getAccessToken(session);
            Response response1 = httpClient.newCall(request).execute();
            if(response1.isSuccessful()){
                return response1.body().string();
            }else{
                return response.body().string();
            }
        }
        return response.body().string();
    }

    private static Response sendRequire(Request request, HttpSession session) throws Exception{
        Response response = httpClient.newCall(request).execute();
        return response;
    }

    /**
     * 同步方法
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute() ;
    }

    private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();

}
