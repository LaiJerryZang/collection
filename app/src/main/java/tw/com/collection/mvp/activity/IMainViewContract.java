package tw.com.collection.mvp.activity;

public interface IMainViewContract {
    void setRefreshing(boolean refreshing);
    void Error(String exception);
    void startProgress();
    void endProgress();
}
