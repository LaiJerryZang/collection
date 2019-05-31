package tw.com.collection.basic.http;

public interface IDataCallListener {

    /**
     * 成功回調處理
     */
    void onSuccess(String response);

    /**
     * 失敗回調處理
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
