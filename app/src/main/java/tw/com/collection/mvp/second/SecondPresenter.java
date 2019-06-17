package tw.com.collection.mvp.second;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import java.io.FileInputStream;
import java.util.Random;

import tw.com.collection.basic.threadpool.ITaskCallListener;
import tw.com.collection.basic.threadpool.ThreadPoolManager;

public class SecondPresenter {
    private SecondViewContract secondViewContract;

    private int[] colors = new int[]{
            Color.parseColor("#039BE5"),
            Color.parseColor("#FFB300"),
            Color.parseColor("#AAAAAA"),
            Color.parseColor("#FFFFFF"),
    };

    public SecondPresenter(SecondViewContract secondViewContract){
        this.secondViewContract = secondViewContract;
    }

    public void randomBackground(){
        secondViewContract.setRandomBackground(colors[new Random().nextInt(4)]);
    }

//    public void getImage(FileInputStream is){
//        ThreadPoolManager.getInstance().addTask(new ITaskCallListener() {
//            @Override
//            public Object work() {
//                return BitmapFactory.decodeStream(is);
//            }
//
//            @Override
//            public void callBack(Object response) {
//                secondViewContract.setImageBackground((Bitmap) response);
//            }
//
//            @Override
//            public void error(String exception) {
//                secondViewContract.Error(exception);
//            }
//        });
//    }

}
