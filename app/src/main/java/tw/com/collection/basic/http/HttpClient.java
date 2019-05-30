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

    //为mOkHttpClient去配置参数  类加载的时候开始创建静态代码块，并且只执行一次
    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true); //设置重定向 其实默认也是true

        /*--添加请求头  这个看个人需求 --*/
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
        /**
         * trust all the https point
         */
        HttpsUtils httpsUtils = new HttpsUtils();
        okHttpClientBuilder.sslSocketFactory(httpsUtils.createSSLSocketFactory(),httpsUtils.mMyTrustManager);
        mOkHttpClient = okHttpClientBuilder.build();
    }

    /**
     *  发送具体的http/https的请求
     * @param request
     * @param httpCallback
     * @return Call
     */
    private static Call sendRequest(Request request, HttpCallback httpCallback){
        Call call=mOkHttpClient.newCall(request);
        call.enqueue(httpCallback);
        return  call;
    }



//    /**
//     *  发送具体的http/https的请求
//     * @param request
//     * @param commonCallback
//     * @return Call
//     */
//    public  static Call sendRequest(Request request, Callback commonCallback){
//        Call call=mOkHttpClient.newCall(request);
//        call.enqueue(commonCallback);
//        return  call;
//    }

    public static void post(String url, String[][] params, IDataCallListener listener){
        listener.start();
        sendRequest(HttpRequest.createPostRequest(url, params), new HttpCallback(listener));
    }

    public static void get(String url, String[][] params, IDataCallListener listener){
        listener.start();
        sendRequest(HttpRequest.createGetRequest(url, params), new HttpCallback(listener));
    }

    public static void postFile(String url, String filename, File file, IDataCallListener listener){
        listener.start();
        sendRequest(HttpRequest.createMultiPostRequest(url,filename,file), new HttpCallback(listener));
    }
}
