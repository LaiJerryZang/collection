package tw.com.collection.basic.socket;

import com.badoo.mobile.util.WeakHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    private int port;

    private CallBack callBack;

    private ExecutorService executorService;

    private ServerSocket serverSocket;

    private WeakHandler handler;

    public interface CallBack {
        void message(String msg);
    }

    public SocketServer(int port, CallBack callBack) {
        this.port = port;
        this.callBack = callBack;

        executorService = Executors.newCachedThreadPool();

        handler = new WeakHandler();
    }

    public void start() {
        executorService.execute(() -> {
            try {
                serverSocket = new ServerSocket(port);
                showMessage(getIpAddressString() + "等待客戶端連線...");
                while (true) {
                    Socket socket = serverSocket.accept();
                    InetAddress address = socket.getInetAddress();
                    showMessage("客戶端 : " + address.getHostAddress() + "已連線");
                    executorService.execute(new Listen(socket, address.getHostAddress()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (executorService != null)
                    executorService.shutdown();
                if (serverSocket != null)
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        });
    }

    private void showMessage(String msg) {
        handler.post(() -> callBack.message(msg));
    }

    class Listen implements Runnable {

        private Socket socket;

        private BufferedReader in;

        private PrintWriter out;

        private String ip;

        private Listen(Socket socket, String ip) {
            this.socket = socket;
            this.ip = ip;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

                out.println(getIpAddressString());
                out.flush();

                while (true) {
                    String str = in.readLine();
                    showMessage(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) socket.close();
                    showMessage(ip + "已下線");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //獲取本地ip
    public static String getIpAddressString() {
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
