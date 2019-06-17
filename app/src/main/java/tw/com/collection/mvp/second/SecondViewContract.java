package tw.com.collection.mvp.second;

import android.graphics.Bitmap;
import android.graphics.Color;

public interface SecondViewContract {

//    void setImageBackground(Bitmap img);

    /**
     * 失敗訊息
     * @param exception 異常錯誤
     */
    void Error(String exception);

    void setRandomBackground(int color);

}
