package tw.com.collection.basic.socket;

import com.badoo.mobile.util.WeakHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    private int port;

    private CallBack callBack;

    private ExecutorService executorService;

    private ServerSocket serverSocket;

    private WeakHandler handler;

    public interface CallBack{
        void message(String msg);
    }

    public SocketServer(int port, CallBack callBack){
        this.port = port;
        this.callBack = callBack;

        executorService = Executors.newCachedThreadPool();
    }

    public void start(){
        executorService.execute(()->{
            try {
                serverSocket = new ServerSocket(port);
                showMessage(serverSocket.getInetAddress().getHostAddress() + "等待客戶端連線...");
                Socket socket = serverSocket.accept();
                InetAddress address = socket.getInetAddress();
                showMessage("客戶端 : " + address.getHostAddress() + "已連線");
                executorService.execute(new Listen(socket));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void showMessage(String msg){
        handler.post(()->callBack.message(msg));
    }

    class Listen implements Runnable{

        private Socket socket;

        private Listen(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

                while (true){

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
