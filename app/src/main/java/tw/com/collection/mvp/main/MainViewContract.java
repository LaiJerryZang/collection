package tw.com.collection.mvp.main;

/**
 * presenter -> main
 */
public interface MainViewContract {
    /**
     * SwipeRefreshLayout 下滑回調
     * @param refreshing 滑動狀態
     */
    void setRefreshing(boolean refreshing);

    /**
     * 失敗訊息
     * @param exception 異常錯誤
     */
    void Error(String exception);

    /**
     * progress開始
     */
    void startProgress();

    /**
     * progress結束
     */
    void endProgress();

    /**
     * Toast訊息
     * @param msg 訊息
     */
    void showToast(String msg);

    /**
     * 設置view觸控
     * @param canTouch
     */
    void setViewTouch(boolean canTouch);

}
