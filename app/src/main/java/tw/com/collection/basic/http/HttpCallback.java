package tw.com.collection.basic.http;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HttpCallback implements Callback {

    private Handler mHandler;
    private IDataCallListener mListener;

    public HttpCallback(IDataCallListener listener){
        this.mListener = listener;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull final IOException e) {
        //在非UI線程，要轉
        mHandler.post(() -> {
            mListener.end();
            mListener.onFailure(e.getClass().getSimpleName());
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        //isSuccessful()判斷回傳code是否為200
        if(response.isSuccessful()){
            assert response.body() != null;
            final String result = response.body().string();
            //在非UI線程，要轉
            mHandler.post(() -> {
                mListener.end();
                mListener.onSuccess(result);
            });
        }
    }
}
