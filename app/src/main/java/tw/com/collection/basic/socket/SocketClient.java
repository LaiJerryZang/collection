package tw.com.collection.basic.socket;

import android.util.Log;

import com.badoo.mobile.util.WeakHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient {

    private String url;

    private int port;

    private CallBack callBack;

    private ExecutorService executorService;

    private WeakHandler handler;

    private Socket socket;

    private BufferedReader in;

    private PrintWriter out;

    public interface CallBack{
        void message(String msg);
    }

    public SocketClient(String url, int port, CallBack callBack){
        this.url = url;
        this.port = port;
        this.callBack = callBack;

        executorService = Executors.newCachedThreadPool();
        handler = new WeakHandler();
    }

    public void start(){
        executorService.execute(()->{
            try {
                socket = new Socket(url,port);
                executorService.execute(new Listen());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void sendMessage(String msg){
        executorService.execute(new Write(msg));
    }

    private void showMessage(String msg){
        handler.post(()->callBack.message(msg));
    }

    class Listen implements Runnable{

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

                while (true){
                    String str = null;
                    while ( (str =in.readLine()) != null){
                        showMessage(str);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    in.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class Write implements Runnable{

        String msg;

        private Write(String msg){
            this.msg = msg;
        }

        @Override
        public void run() {
            out.println(msg);
            out.flush();
            Log.d("write_aaa",msg);
        }
    }
}
