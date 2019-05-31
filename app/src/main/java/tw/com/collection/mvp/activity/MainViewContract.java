package tw.com.collection.mvp.activity;

public interface MainViewContract {
    void setRefreshing(boolean refreshing);
    void Error(String exception);
    void startProgress();
    void endProgress();
    void showToast(String msg);
}
