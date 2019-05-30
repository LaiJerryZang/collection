package tw.com.collection.basic.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
    private static ThreadPoolManager Instance;
    private static ExecutorService threadpool;

    /**
     * 取得單例物件
     */
    public static ThreadPoolManager getInstance() {
        if (Instance == null) {
            return new ThreadPoolManager();
        }
        return Instance;
    }

    private ThreadPoolManager() {
        if (threadpool == null) {
            /**
             * corePoolSize 核心線程 1
             * maximumPoolSize 最大線程數量 100
             * keepAliveTime 線程空閒時間 15 (超過15秒被回收)
             * TimeUnit 單位 秒
             * PriorityBlockingQueue 優先級列隊
             */
            threadpool = new ThreadPoolExecutor(1, 100, 15, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        }
    }

    /**
     * 列隊加入線程
     *
     * @param listener 回調介面
     * @param priority 線程執行的優先級 0:高 1:中 2:低
     */
    public final void addTask(ITaskCallListener listener, int priority) {
//        taskQueue.add(new Task(listener, priority));
        threadpool.execute(new Task(listener,priority));
    }
}
