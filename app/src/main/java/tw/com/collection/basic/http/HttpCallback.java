package tw.com.collection.basic.http;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HttpCallback implements Callback {

    private Handler mHandler;
    private IDataCallListener mListener;

    public HttpCallback(IDataCallListener IDataCallListener){
        this.mListener = IDataCallListener;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        //在非UI線程，要轉
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.end();
                mListener.onFailure(e.getClass().getSimpleName());
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        //isSuccessful()判斷回傳code是否為200
        if(response.isSuccessful()){
            final String result = response.body().string();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.end();
                    mListener.onSuccess(result);
                }
            });
        }
    }
}
