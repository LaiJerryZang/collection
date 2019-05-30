package tw.com.collection.basic.http;

public interface IDataCallListener {

    /**
     * 请求成功回调事件处理
     */
    void onSuccess(String response);

    /**
     * 请求失败回调事件处理
     */
    void onFailure(String response);

    /**
     * progress開始
     */
    void start();

    /**
     * progress結束
     */
    void end();
}
