package tw.com.collection.basic.threadpool;

public interface ITaskCallListener {
    /**
     * 執行並回傳物件 子執行緒
     */
    Object work();

    /**
     * 處理回傳的物件 主執行緒
     */
    void callBack(Object response);

    /**
     * 失敗回調處理 主執行緒
     */
    void error(String exception);
}
