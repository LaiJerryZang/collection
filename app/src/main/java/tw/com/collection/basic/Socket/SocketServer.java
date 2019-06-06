package tw.com.collection.basic.Socket;

import com.badoo.mobile.util.WeakHandler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    private ServerSocket socketServer;

    private Map<String, BufferedOutputStream> clientInfo;//存放連線的輸出流

    private ExecutorService executorService;

    private WeakHandler handler = new WeakHandler();

    private SocketCallBack socketCallBack;//訊息回調

    public interface SocketCallBack{
        void MessageBack(String msg);
    }

    public SocketServer(SocketCallBack socketCallBack){
        try {
            socketServer = new ServerSocket(6666);
            clientInfo = new HashMap<>();
            executorService = Executors.newCachedThreadPool();
            this.socketCallBack = socketCallBack;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void start(){
        try {
            while (true){
                handler.post(()->socketCallBack.MessageBack("等待連線中"));
                Socket socket = socketServer.accept();//等待連接
                InetAddress address = socket.getInetAddress();//取得ip
                handler.post(()->socketCallBack.MessageBack(address+"連線成功"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 将客户端的信息以Map形式存入集合中
    private void putIn(String key, BufferedOutputStream value) {
        synchronized (this) {
            clientInfo.put(key, value);
        }
    }

    // 将给定的输出流从共享集合中删除
    private synchronized void remove(String key) {
        clientInfo.remove(key);
        handler.post(()->socketCallBack.MessageBack("在線人數為" + clientInfo.size()));
    }

    // 将给定的消息转发给所有客户端
    private synchronized void sendToAll(String message) throws Exception{
        for (BufferedOutputStream out : clientInfo.values()) {
            sendMessage(out,message);
        }
    }

    // 将给定的消息转发给私聊的客户端
    private synchronized void sendToSomeone(String name, String message) throws Exception{
        BufferedOutputStream out = clientInfo.get(name); // 将对应客户端的聊天信息取出作为私聊内容发送出去
        if (out != null)
            sendMessage(out,message);
    }

    private class ReadThread implements Runnable{
        byte[] data = new byte[1024];
        private Socket socket;

        public ReadThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                /*
                 * 通过客户端的Socket获取客户端的输出流 用来将消息发送给客户端
                 */
                BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
                /*
                 * 将客户昵称和其所说的内容存入共享集合HashMap中
                 */
                String name = getName();
                putIn(name, out);
                Thread.sleep(100);
                // 服务端通知所有客户端，某用户上线
                sendToAll("[系统通知] `" + name + "`上線");
                /*
                 * 通过客户端的Socket获取输入流 读取客户端发送来的信息
                 */
                BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                String msgString = null;
                int size = 0;
                while ((size = in.read(data)) != -1) {
                    // 检验是否为私聊（格式：@昵称：内容）
                    if (msgString.startsWith("@")) {
                        int index = msgString.indexOf(":");
                        if (index >= 0) {
                            // 获取昵称
                            String theName = msgString.substring(1, index);
                            String info = msgString.substring(index + 1, msgString.length());
                            info = name + "：" + info;
                            // 将私聊信息发送出去
                            sendToSomeone(theName, info);
                            continue;
                        }
                    }
                    // 遍历所有输出流，将该客户端发送的信息转发给所有客户端
                    System.out.println(name + "：" + msgString);
                    sendToAll(name + "：" + msgString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String getName(){
            try {
                // 服务端的输入流读取客户端发送来的昵称输出流
                BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                // 服务端将昵称验证结果通过自身的输出流发送给客户端
                BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

                // 读取客户端发来的昵称
                while (true) {
                    String name = new String(data, 0, in.read(data));
                    if ((name.trim().length() == 0) || clientInfo.containsKey(name)) {
                        out.write("fault to client".getBytes());
                    } else {
                        out.write("ok".getBytes());
                        return name;
                    }
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
            return null;
        }

    }

    private void sendMessage(BufferedOutputStream out, String msg)throws Exception{
        out.write(msg.getBytes());
        out.flush();
    }

}
