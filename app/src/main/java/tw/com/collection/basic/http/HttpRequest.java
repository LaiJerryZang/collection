package tw.com.collection.basic.http;

import java.io.File;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpRequest {

    //post
    public static Request createPostRequest(String url, String[][] params){

        FormBody.Builder mFormBodybuilder = new FormBody.Builder();
        if(params!=null){

            for(String[] value: params){
                // 将请求参数逐一添加到请求体中
                mFormBodybuilder.add(value[0],value[1]);
            }
        }
        FormBody mFormBody=mFormBodybuilder.build();
        return  new Request.Builder()
                .url(url)
                .post(mFormBody)
                .build();
    }

    //get
    public static Request createGetRequest(String url, String[][] params){

        StringBuilder urlBuilder=new StringBuilder(url).append("?");
        if(params!=null){
            for(String[] value: params){
                // 将请求参数逐一添加到请求体中
                urlBuilder.append(value[0]).append("=")
                        .append(value[1])
                        .append("&");
            }
        }
        return  new Request.Builder()
                .url(urlBuilder.substring(0,urlBuilder.length()-1)) //要把最后的&符号去掉
                .get()
                .build();
    }

    //文件流
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    public static Request createMultiPostRequest(String url, String filename, File file) {

        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        if (file != null) {
            requestBody.addFormDataPart(filename, filename+".png", RequestBody.create(FILE_TYPE, file));
        }
        return new Request.Builder()
                .url(url)
                .post(requestBody.build())
                .build();
    }
}
