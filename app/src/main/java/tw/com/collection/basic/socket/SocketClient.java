package tw.com.collection.basic.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient {
    private Socket socket;
    private String url;
    private int port;
    private String name;
    private SocketCallBack socketCallBack;//訊息回調

    public interface SocketCallBack{
        void MessageBack(String msg);
    }

    public SocketClient(String url, int port, String name, SocketCallBack socketCallBack){
        this.url = url;
        this.port = port;
        this.name = name;
        this.socketCallBack = socketCallBack;
    }

    public void start(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            try {
                socket = new Socket(url,port);
                BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
                sendMessage(out,"jerry");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.execute(new ListenrServser());
    }

    class ListenrServser implements Runnable {
        byte[] data = new byte[1024];
        @Override
        public void run() {
            try {
                BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                StringBuilder stringBuilder = new StringBuilder();
                // 读取客户端发来的昵称
                while (in.read(data) != -1) {
                    stringBuilder.append(new String(data, 0, in.read(data)));
                }
                socketCallBack.MessageBack(stringBuilder.toString());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(BufferedOutputStream out, String msg)throws Exception{
        out.write(msg.getBytes());
        out.flush();
    }
}
