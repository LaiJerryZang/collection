package tw.com.collection.basic.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CommonUtil {

    private static long lastClickTime;

    //避免重複點擊
    public static boolean isFastToClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 1000) {
            return false;
        }
        lastClickTime = time;
        return true;
    }

    //檢查網路
    public static Boolean checkinternet(Context context) {
        ConnectivityManager cManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cManager.getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }

    //動畫
    public static void animation(Object o, String propertyName, int sec, float... floats){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(o, propertyName, floats);
        objectAnimator.setDuration(sec);
        objectAnimator.start();
    }

    //顯示Toast
    public static void showToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
