package tw.com.collection.basic.threadpool;

import android.os.Handler;
import android.os.Message;

import com.badoo.mobile.util.WeakHandler;

public class Task implements Runnable, Comparable<Task>{

    private ITaskCallListener listener;

    private int priority;

    public int getPriority() {
        return priority;
    }

    /**
     * @param listener 回調介面
     * @param priority 線程執行的優先級 0:高 1:中 2:低
     */
    public Task(ITaskCallListener listener, int priority){
        this.listener = listener;
        this.priority = priority;
    }

    @Override
    public void run() {
        Object obj = null;
        try {
            obj = listener.work();
        } catch (Exception e) {
            weakHandler.sendMessage(setMessage(1,e.getClass().getSimpleName()));
        }

        if(obj == null){
            weakHandler.sendMessage(setMessage(1,"task return null"));
        }else {
            weakHandler.sendMessage(setMessage(0,obj));
        }
    }

    private WeakHandler weakHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listener.callBack(msg.obj);//成功回調
                    break;
                case 1:
                    listener.error(msg.obj.toString());//失敗回調
                    break;
            }
            return true;
        }
    });

    private Message setMessage(int what, Object obj){
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        return message;
    }

    /**
     * 比較執行緒優先級
     */
    @Override
    public int compareTo(Task task) {

        if(this.getPriority() > task.getPriority()) {
            return 1;
        } else if (this.getPriority() < task.getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }
}
