package tw.com.collection.basic.socket;

import android.util.Log;

import com.badoo.mobile.util.WeakHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketClient {

    private String url;

    private int port;

    private CallBack callBack;

    private ExecutorService executorService;

    private WeakHandler handler;

    private Socket socket;

    private BufferedReader in;

    private PrintWriter out;

    private boolean isClient;

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
                isClient = true;
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

    public void close(){
        isClient = false;
        executorService.execute(new Write("client exit"));
        Log.i("下線","下線");
    }

    class Listen implements Runnable{

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

                String str;
                while (isClient){
                    if ((str =in.readLine()) != null){
                        showMessage(str);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                showMessage("連線中斷");
                executorService.shutdown();
                try {
                    while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {}
                    in.close();
                    out.close();
                    socket.close();
                } catch (Exception e) {
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
        }
    }

    //獲取本地ip
    private static String getIpAddressString() {
        try {
            for (Enumeration<NetworkInterface> enNetI = NetworkInterface
                    .getNetworkInterfaces(); enNetI.hasMoreElements(); ) {
                NetworkInterface netI = enNetI.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = netI
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

}
