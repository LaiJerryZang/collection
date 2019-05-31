package tw.com.collection.basic.http;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpClient {

    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    //mOkHttpClient配置参数  類加载的时候創建靜態代碼區塊，只執行一次
    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true); //设置重定向 其实默认也是true

        /*--設置請求頭 --*/
//        okHttpClientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request()
//                        .newBuilder()
//                        .addHeader("User-Agent", "Android—Mobile") // 标明发送本次请求的客户端
//                        .build();
//                return chain.proceed(request);
//            }
//        });

        //添加https支持
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        //信任所有憑證
        HttpsUtils httpsUtils = new HttpsUtils();
        okHttpClientBuilder.sslSocketFactory(httpsUtils.createSSLSocketFactory(),httpsUtils.mMyTrustManager);

        mOkHttpClient = okHttpClientBuilder.build();
    }

    /**
     * 發送http/https請求
     * @param request url參數
     * @param httpCallback 結果回調
     */
    private static Call sendRequest(Request request, HttpCallback httpCallback){
        Call call=mOkHttpClient.newCall(request);
        call.enqueue(httpCallback);
        return  call;
    }

    /**
     * http post
     * @param url 網址
     * @param params url參數
     * @param listener 回調
     */
    public static void post(String url, String[][] params, IDataCallListener listener){
        listener.start();
        sendRequest(HttpRequest.createPostRequest(url, params), new HttpCallback(listener));
    }

    /**
     * http get
     * @param url 網址
     * @param params url參數
     * @param listener 回調
     */
    public static void get(String url, String[][] params, IDataCallListener listener){
        listener.start();
        sendRequest(HttpRequest.createGetRequest(url, params), new HttpCallback(listener));
    }

    /**
     * http post
     * @param url 網址
     * @param filename 檔案名稱
     * @param file 檔案
     * @param listener 回調
     */
    public static void postFile(String url, String filename, File file, IDataCallListener listener){
        listener.start();
        sendRequest(HttpRequest.createMultiPostRequest(url,filename,file), new HttpCallback(listener));
    }
}
