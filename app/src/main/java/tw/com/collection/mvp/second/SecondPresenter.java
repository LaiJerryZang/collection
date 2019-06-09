package tw.com.collection.mvp.second;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;

import tw.com.collection.basic.threadpool.ITaskCallListener;
import tw.com.collection.basic.threadpool.ThreadPoolManager;

public class SecondPresenter {
    private SecondViewContract secondViewContract;

    public SecondPresenter(SecondViewContract secondViewContract){
        this.secondViewContract = secondViewContract;
    }

    public void getImage(FileInputStream is){
        ThreadPoolManager.getInstance().addTask(new ITaskCallListener() {
            @Override
            public Object work() {
                return BitmapFactory.decodeStream(is);
            }

            @Override
            public void callBack(Object response) {
                secondViewContract.setImageBackground((Bitmap) response);
            }

            @Override
            public void error(String exception) {
                secondViewContract.Error(exception);
            }
        });
    }

    public void Destroy() {
        secondViewContract = null;
    }
}
