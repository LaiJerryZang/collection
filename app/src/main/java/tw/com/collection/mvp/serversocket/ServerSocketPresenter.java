package tw.com.collection.mvp.serversocket;

import tw.com.collection.basic.socket.SocketServer;

public class ServerSocketPresenter implements SocketServer.CallBack {

    private ServerSocketViewContract serverSocketViewContract;

    private SocketServer socketServer;

    public ServerSocketPresenter(ServerSocketViewContract serverSocketViewContract){
        this.serverSocketViewContract = serverSocketViewContract;
        socketServer = new SocketServer(6666,this);
        socketServer.start();
    }

    public void close(){
        socketServer.close();
    }

    public void sendMessage(String msg){
        socketServer.sendMessage(msg);
    }

    @Override
    public void message(String msg) {
        serverSocketViewContract.showMessage(msg);
    }
}
