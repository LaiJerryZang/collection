package tw.com.collection.basic.threadpool;

public interface ITaskCallListener {
    Object work();

    void callBack(Object response);

    void error(String exception);
}
